package com.musicianhelper.login.impl.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.musicianhelper.login.impl.components.SnackBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel) {

  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val state by viewModel.observeState().collectAsState()


  state.error?.let { text ->
    coroutineScope.launch {
      scaffoldState.snackbarHostState.showSnackbar(text)
    }
  }

  Scaffold(
    scaffoldState = scaffoldState,
    snackbarHost = { scaffoldState.snackbarHostState }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      LoginOutlinedField(label = "Email", icon = Icons.Default.Email)
      LoginOutlinedField(label = "Password", icon = Icons.Default.Lock)

      TextButton(
        onClick = {
          viewModel.dispatch(LoginEvent.Login("Dima@dasd.com", "123456"))
        },
        modifier = Modifier.wrapContentWidth(),
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Blue),
      ) {
        Text(
          color = Color.White,
          text = "Login"
        )
      }
    }
    SnackBar(
      snackbarHostState = scaffoldState.snackbarHostState,
      onDismiss = { scaffoldState.snackbarHostState.currentSnackbarData?.dismiss() },
    )
  }
}

@Composable
internal fun LoginOutlinedField(
  label: String = "",
  icon: ImageVector? = null,
  contentDesc: String? = null
) {
  var value by remember { mutableStateOf(TextFieldValue("")) }

  OutlinedTextField(
    value = value,
    label = { Text(text = label) },
    onValueChange = { value = it },
    leadingIcon = {
      icon?.let {
        Icon(
          imageVector = icon,
          contentDescription = contentDesc,
        )
      }
    },
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(horizontal = 36.dp, vertical = 16.dp),
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = Color.Blue,
      unfocusedBorderColor = Color.Black
    ),
    isError = false,
    singleLine = true
  )
}
