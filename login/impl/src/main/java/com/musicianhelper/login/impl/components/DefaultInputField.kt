package com.musicianhelper.login.impl.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
internal fun DefaultOutlinedField(
  modifier: Modifier = Modifier,
  defaultValue: MutableState<TextFieldValue>,
  label: String = "",
  icon: ImageVector? = null,
  description: String? = null,
  keyboardActions: KeyboardActions
) {

  OutlinedTextField(
    value = defaultValue.value,
    label = { Text(text = label) },
    onValueChange = { defaultValue.value = it },
    leadingIcon = {
      icon?.let {
        Icon(
          imageVector = icon,
          contentDescription = description,
        )
      }
    },
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(horizontal = 36.dp, vertical = 16.dp),
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = Color.Blue,
      unfocusedBorderColor = Color.Black
    ),
    isError = false,
    singleLine = true,
    keyboardOptions = KeyboardOptions.Default.copy(
      capitalization = KeyboardCapitalization.Sentences,
      autoCorrect = true,
      keyboardType = KeyboardType.Ascii,
      imeAction = ImeAction.Done
    ),
    keyboardActions = keyboardActions
  )
}
