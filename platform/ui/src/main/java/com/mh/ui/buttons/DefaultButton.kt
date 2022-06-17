package com.mh.ui.buttons

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = { },
  buttonText: String,
  isEnabled: Boolean
) {

  TextButton(
    enabled = isEnabled,
    onClick = onClick,
    modifier = modifier.wrapContentWidth(),
    colors = if (isEnabled) {
      ButtonDefaults.textButtonColors(backgroundColor = Color.Blue)
    } else {
      ButtonDefaults.textButtonColors(backgroundColor = Color.Gray)
    },
  ) {
    Text(
      color = Color.White,
      text = buttonText
    )
  }
}
