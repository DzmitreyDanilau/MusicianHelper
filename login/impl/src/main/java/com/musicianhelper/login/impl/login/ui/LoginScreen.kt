package com.musicianhelper.login.impl.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.musicianhelper.data.AuthThrowable
import com.musicianhelper.login.impl.LoginEntryPoint
import com.musicianhelper.login.impl.components.DefaultButton
import com.musicianhelper.login.impl.components.DefaultOutlinedField
import com.musicianhelper.login.impl.components.DefaultSnackbar
import com.musicianhelper.login.impl.login.ui.LoginEvent.DismissSnackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO seems like each time the user navigates to the another composable, we trigger
// TODO recomposition, that causes displaying snack bar 3 times.
// TODO Need to investigate that issue

// TODO Also, if we try to dismiss the snackBar and hide sign up offer, ui starts flickering
// TODO it seems like navigation happens with delay

@OptIn(ExperimentalCoroutinesApi::class)
@FlowPreview
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val state by viewModel.collectState().collectAsState()
    var isRegisterVisible by remember{ mutableStateOf(false)}

    when (state) {
        is LoginState.Fail -> {
            val error = (state as LoginState.Fail).error as AuthThrowable
            if (error.code == 903) {
                isRegisterVisible = true
            }
            error.errorText.let { text ->
                coroutineScope.launch {
                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = text,
                        actionLabel = "Dismiss"
                    )
                    if (snackbarResult == SnackbarResult.Dismissed) {
                        viewModel.dispatch(DismissSnackbar)
                    }
                }
            }
        }
        else -> {
            // do nothing for now
        }
    }

    var email by remember { mutableStateOf("test@gmail.com") }
    var password by remember { mutableStateOf("1234") }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState },
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val focusManager = LocalFocusManager.current

                DefaultOutlinedField(
                    value = email,
                    onValueChange = { textValue -> email = textValue },
                    label = "Email",
                    icon = Icons.Default.Email,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
                DefaultOutlinedField(
                    value = password,
                    onValueChange = { textValue -> password = textValue },
                    label = "Password",
                    icon = Icons.Default.Lock,
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
                DefaultButton(
                    isEnabled = email.isNotBlank(),
                    buttonText = "Login",
                    onClick = { viewModel.dispatch(LoginEvent.Login(email, password)) })

                if (isRegisterVisible) {
                    AnnotatedClickableText(
                        text = "Don't have an account? ",
                        textColor = Color.Black,
                        tag = "Sign up",
                        tagColor = Color.Red,
                        onClick = {
                            navController.navigate(LoginEntryPoint.InternalRoutes.REGISTRATION)
                        }
                    )
                }
            }

            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                onDismiss = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }

    LaunchedEffect(coroutineScope) {
        launch {
            viewModel.collectNavigation().collectLatest {
                when (it) {
                    NavigateToRegistration -> {
                        navController.navigate(LoginEntryPoint.InternalRoutes.REGISTRATION)
                    }
                }
            }
        }
    }
}
