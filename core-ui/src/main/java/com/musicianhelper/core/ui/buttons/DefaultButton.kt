package com.musicianhelper.core.ui.buttons

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  buttonText: String,
  isEnabled: Boolean
) {

  TextButton(
    enabled = isEnabled,
    onClick = onClick,
    modifier = modifier.wrapContentWidth(),
    colors = if (isEnabled) {
      ButtonDefaults.textButtonColors(containerColor = Color.Blue)
    } else {
      ButtonDefaults.textButtonColors(containerColor = Color.Gray)
    },
  ) {
    Text(
      color = Color.White,
      text = buttonText
    )
  }
}
