package com.musicianhelper.core.ui.snackbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSnackbar(
  snackbarHostState: SnackbarHostState,
  modifier: Modifier = Modifier,
  onDismiss: () -> Unit = { }
) {
  SnackbarHost(
    hostState = snackbarHostState,
    snackbar = { data ->
      Snackbar(
        modifier = Modifier.padding(16.dp),
        content = {
          Text(
            text = data.visuals.message,
            style = MaterialTheme.typography.bodySmall
          )
        },
        action = {
          data.visuals.actionLabel?.let { actionLabel ->
            TextButton(onClick = onDismiss) {
              Text(
                text = actionLabel,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall
              )
            }
          }
        }
      )
    },
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight(Alignment.Bottom)
  )
}