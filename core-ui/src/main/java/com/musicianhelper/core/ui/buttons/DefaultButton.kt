package com.musicianhelper.core.ui.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  buttonText: String,
  isEnabled: Boolean
) {
  val interactionSource = remember { MutableInteractionSource() }
  TextButton(
    enabled = isEnabled,
    modifier = modifier.wrapContentWidth().clickable(
      interactionSource = interactionSource,
      indication = rememberRipple(true),
      onClick = onClick
    ),
    onClick = onClick,
    colors =
      ButtonDefaults.textButtonColors(containerColor = Color.Blue)
   ,
  ) {
    Text(
      color = Color.White,
      text = buttonText
    )
  }
}
