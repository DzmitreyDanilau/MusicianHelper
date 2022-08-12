package com.musicianhelper.core.common

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

@SuppressLint("ComposableNaming")
@Composable
fun <LO : LifecycleObserver> LO.observeLifecycle(lifecycle: Lifecycle) {
  DisposableEffect(lifecycle) {
    lifecycle.addObserver(this@observeLifecycle)
    onDispose {
      lifecycle.removeObserver(this@observeLifecycle)
    }
  }
}