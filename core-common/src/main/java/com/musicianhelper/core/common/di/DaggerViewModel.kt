package com.musicianhelper.core.common.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified VM : ViewModel> injectedViewModel(
  key: String? = null,
  crossinline viewModelInstanceCreator: () -> VM
): VM = viewModel(
  key = key,
  factory = object : Factory {
    override fun <VM : ViewModel> create(modelClass: Class<VM>): VM {
      @Suppress("UNCHECKED_CAST")
      return viewModelInstanceCreator() as VM
    }
  }
)