package com.musicianhelper.login.impl.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import com.musicianhelper.core.common.data.AuthThrowable
import com.musicianhelper.core.ui.buttons.DefaultButton
import com.musicianhelper.core.ui.inputfields.DefaultOutlinedField
import com.musicianhelper.core.ui.snackbar.DefaultSnackbar
import com.musicianhelper.core.ui.text.AnnotatedClickableText
import com.musicianhelper.login.impl.ui.LoginEvent.DismissSnackbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(
  ExperimentalCoroutinesApi::class,
  ExperimentalMaterial3Api::class,
)
@FlowPreview
@Composable
internal fun LoginScreen(
  navController: NavController,
  viewModel: LoginViewModel,
  state: LoginState
) {

  val coroutineScope = rememberCoroutineScope()
  val snackBarHostState = remember { SnackbarHostState() }
  val progressBarVisibility = remember { mutableStateOf(false) }

  when (state) {
    is LoginState.Fail -> {
      if (state.isSnackBarVisible) {
        progressBarVisibility.value = state.isLoading
        state.error?.let {
          val text = (it as AuthThrowable).errorText
          coroutineScope.launch {
            val snackbarResult = snackBarHostState.showSnackbar(
              message = text,
              actionLabel = "Dismiss"
            )
            println("Action from snackbar: $snackbarResult")
            if (snackbarResult == SnackbarResult.Dismissed) {
              viewModel.dispatchEvent(DismissSnackbar)
            }
          }
        }
      }
    }
    is LoginState.Loading -> {
      progressBarVisibility.value = state.isLoading
    }
    else -> progressBarVisibility.value = state.isLoading
  }

  var email by remember { mutableStateOf("test@gmail.com") }
  var password by remember { mutableStateOf("1234") }

  Scaffold(
    topBar = {
      AnimatedVisibility(visible = progressBarVisibility.value) {
        LinearProgressIndicator(
          modifier = Modifier.fillMaxWidth()
        )
      }
    },

    snackbarHost = {
      DefaultSnackbar(snackbarHostState = snackBarHostState) {
        snackBarHostState.currentSnackbarData?.dismiss()
      }
    },
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
            onDone = { focusManager.moveFocus(FocusDirection.Down) }
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
}
