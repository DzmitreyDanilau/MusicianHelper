package com.musicianhelper.login.impl.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.musicianhelper.login.impl.LoginEntryPoint.InternalRoutes
import com.musicianhelper.login.impl.components.DefaultButton
import com.musicianhelper.login.impl.components.DefaultOutlinedField
import com.musicianhelper.login.impl.components.DefaultSnackbar
import com.musicianhelper.login.impl.login.ui.LoginEvent.DismissSnackbar
import com.musicianhelper.login.impl.login.ui.LoginEvent.Login
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

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
      (state as LoginState.Fail).error?.message?.let { text ->
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
  }

  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }

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
          // onClick = { viewModel.dispatch(Login(email, password)) })
          onClick = { navController.navigate(InternalRoutes.REGISTRATION) })
      }

      DefaultSnackbar(
        snackbarHostState = scaffoldState.snackbarHostState,
        onDismiss = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() },
        modifier = Modifier.align(Alignment.BottomCenter)
      )
    }
  }
}
