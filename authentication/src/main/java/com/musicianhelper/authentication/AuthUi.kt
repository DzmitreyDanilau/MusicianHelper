package com.musicianhelper.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
  TextFieldWithIcons()
}

@Composable
internal fun TextFieldWithIcons() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    LoginOutlinedField(text = "Email", icon = Icons.Default.Email)
    LoginOutlinedField(text = "Password", icon = Icons.Default.Lock)
    TextButton(
      onClick = { /*TODO*/ },
      modifier = Modifier
        .wrapContentWidth(),
      colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Blue),
    ) {
      Text(
        color = Color.White,
        text = "Login"
      )
    }
  }
}

@Composable
internal fun LoginOutlinedField(
  text: String = "",
  icon: ImageVector? = null,
  contentDesc: String? = null
) {
  var value by remember { mutableStateOf(TextFieldValue(text)) }

  OutlinedTextField(
    value = value,
    label = { OutlinedTextFieldText(text = text) },
    onValueChange = { value = it },
    leadingIcon = {
      icon?.let {
        LoginOutlinedIcon(it, contentDesc)
      }
    },
    modifier = Modifier
      .fillMaxWidth()
      .height(60.dp)
      .padding(horizontal = 36.dp, vertical = 16.dp),
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = Color.Blue,
      unfocusedBorderColor = Color.Black
    ),
    isError = false,
    singleLine = true
  )
}

@Composable
internal fun OutlinedTextFieldText(text: String) {
  Text(text = text)
}

@Composable
internal fun LoginOutlinedIcon(
  icon: ImageVector,
  contentDesc: String?
) {
  Icon(
    imageVector = icon,
    contentDescription = contentDesc,
  )
}
