package com.musicianhelper.core.ui.inputfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultOutlinedField(
  modifier: Modifier = Modifier,
  trailingIcon: @Composable (() -> Unit)? = null,
  value: String = "",
  label: @Composable (() -> Unit),
  isError: Boolean = false,
  isSingleLine: Boolean = true,
  icon: @Composable (() -> Unit)? = null,
  description: String? = null,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  onValueChange: ((String) -> Unit)? = null
) {
  OutlinedTextField(
    trailingIcon = { trailingIcon?.invoke() },
    value = value,
    label = label,
    onValueChange = { text -> onValueChange?.invoke(text) },
    leadingIcon = { icon?.invoke() },
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(horizontal = 36.dp, vertical = 16.dp),
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = Color.Blue,
      unfocusedBorderColor = Color.Black
    ),
    isError = isError,
    singleLine = isSingleLine,
    keyboardOptions = KeyboardOptions.Default,
    keyboardActions = keyboardActions
  )
}
