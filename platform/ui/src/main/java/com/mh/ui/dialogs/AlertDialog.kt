package com.mh.ui.dialogs

import androidx.compose.runtime.Composable

@Composable
fun AlertDialog(
  title: String,
  description: String,
  buttons: @Composable () -> Unit,
  dismissRequest: (() -> Unit)? = null
) {
  androidx.compose.material.AlertDialog(
    onDismissRequest = { dismissRequest?.invoke() },
    buttons = { buttons.invoke() }
  )
}