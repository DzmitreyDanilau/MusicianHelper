package com.musicianhelper.login.impl.ui

import androidx.compose.animation.*
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
import com.musicianhelper.login.impl.components.DefaultButton
import com.musicianhelper.login.impl.components.DefaultOutlinedField
import com.musicianhelper.login.impl.components.DefaultSnackbar
import com.musicianhelper.login.impl.ui.LoginEvent.DismissSnackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

var counter = 0

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

    when (state) {
        is LoginState.Fail -> {
            if (state.isSnackBarVisible) {
                state.error?.let {
                    val text = (it as AuthThrowable).errorText
                    LaunchedEffect(scaffoldState.snackbarHostState) {
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
        }
        else -> {
            Timber.d("State $state")
        }
    }

    SideEffect {
        Timber.d("Recomposition ${counter++}")
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

                AnimatedVisibility(
                        visible = state.isSignUpVisible,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                ) {
                    AnnotatedClickableText(
                            text = "Don't have an account? ",
                            textColor = Color.Black,
                            tag = "Sign up",
                            tagColor = Color.Red,
                            onClick = { viewModel.dispatch(LoginEvent.SignUpClicked) }
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
                        navController.navigate("registration")
                    }
                }
            }
        }
    }
}
