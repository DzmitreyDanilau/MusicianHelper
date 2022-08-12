package com.musicianhelper.login.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import com.musicianhelper.core.common.data.AuthThrowable
import com.musicianhelper.core.common.observeLifecycle
import com.musicianhelper.core.ui.buttons.DefaultButton
import com.musicianhelper.core.ui.inputfields.DefaultOutlinedField
import com.musicianhelper.core.ui.snackbar.DefaultSnackbar
import com.musicianhelper.core.ui.text.AnnotatedClickableText
import com.musicianhelper.login.impl.ui.LoginEvent.DismissSnackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@FlowPreview
@Composable
fun LoginScreen(
  navController: NavController,
  viewModel: LoginViewModel
) {

  viewModel.observeLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)

  val coroutineScope = rememberCoroutineScope()
  val state by viewModel.collectState().collectAsState()
  val snackBarHostState = remember { SnackbarHostState() }

  when (state) {
    is LoginState.Fail -> {
      if (state.isSnackBarVisible) {
        state.error?.let {
          val text = (it as AuthThrowable).errorText
          LaunchedEffect(snackBarHostState) {
            val snackbarResult = snackBarHostState.showSnackbar(
              message = text,
              actionLabel = "Dismiss"
            )
            if (snackbarResult == SnackbarResult.Dismissed) {
              viewModel.dispatchEvent(DismissSnackbar)
            }
          }
        }
      }
    }
    else -> {}
  }

  var email by remember { mutableStateOf("test@gmail.com") }
  var password by remember { mutableStateOf("1234") }

  Scaffold(
    topBar = {
      SmallTopAppBar(title = { Text("Login") })
    },
    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },

    ) {
    Box(modifier = Modifier.padding(it)) {
      Column(
        modifier = Modifier
          .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {

        val focusManager = LocalFocusManager.current

        DefaultOutlinedField(
          value = email,
          onValueChange = { textValue -> email = textValue },
          label = { Text(text = "Email") },
          icon = {
            Icon(
              imageVector = Icons.Default.Email,
              contentDescription = null,
            )
          },
          keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
          )
        )
        DefaultOutlinedField(
          value = password,
          onValueChange = { textValue -> password = textValue },
          label = { Text(text = "Password") },
          icon = {
            Icon(
              imageVector = Icons.Default.Lock,
              contentDescription = null,
            )
          },
          keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
        DefaultButton(
          isEnabled = email.isNotBlank(),
          buttonText = "Login",
          onClick = { viewModel.dispatchEvent(LoginEvent.Login(email, password)) }
        )

        this@Column.AnimatedVisibility(
          visible = state.isSignUpVisible,
          enter = fadeIn() + expandHorizontally(),
          exit = fadeOut() + shrinkHorizontally()
        ) {
          AnnotatedClickableText(
            text = "Don't have an account? ",
            textColor = Color.Black,
            tag = "Sign up",
            tagColor = Color.Red,
            onClick = { viewModel.dispatchEvent(LoginEvent.SignUpClicked) }
          )
        }
      }

      DefaultSnackbar(
        snackbarHostState = snackBarHostState,
        onDismiss = { snackBarHostState.currentSnackbarData?.dismiss() },
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
