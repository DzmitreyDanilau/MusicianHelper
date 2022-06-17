package com.mh.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun ColumnScope.AnimatedVisibilityItem(
  content: @Composable () -> Unit,
  height: Dp,
  isVisible: Boolean,
  padding: Dp = 8.dp,
  enterTransition: EnterTransition = fadeIn() + expandHorizontally(),
  exitTransition: ExitTransition = fadeOut() + shrinkHorizontally()
) {

  SideEffect {
    Timber.d("isVisible: $isVisible")
  }

  Box(
    modifier = Modifier
      .height(height)
      .padding(padding)
      .wrapContentWidth()
  ) {
    this@AnimatedVisibilityItem.AnimatedVisibility(
      visible = isVisible,
      enter = enterTransition,
      exit = exitTransition
    ) {
      content()
    }
  }
}
