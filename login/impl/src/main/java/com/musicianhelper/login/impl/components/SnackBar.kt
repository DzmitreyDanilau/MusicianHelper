package com.musicianhelper.login.impl.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnackBar(
  snackbarHostState: SnackbarHostState,
  onDismiss: () -> Unit
) {

  Box(modifier = Modifier.padding(16.dp)) {
    SnackbarHost(
      hostState = snackbarHostState,
      snackbar = { data ->
        Snackbar(
          modifier = Modifier.align(Alignment.BottomCenter),
          content = { Text(text = data.message) },
          action = {
            TextButton(onClick = onDismiss) {
              Text(text = "OK")
            }
          },
        )
      }
    )
  }
}
