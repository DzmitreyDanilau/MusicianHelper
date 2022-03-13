package com.musicianhelper.login.impl.components

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
  buttonText: String
) {
  TextButton(
    onClick = onClick,
    //
    modifier = modifier.wrapContentWidth(),
    colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Blue),
  ) {
    Text(
      color = Color.White,
      text = buttonText
    )
  }
}