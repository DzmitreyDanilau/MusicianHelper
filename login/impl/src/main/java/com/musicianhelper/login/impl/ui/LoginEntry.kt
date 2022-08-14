package com.musicianhelper.login.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.musicianhelper.core.common.observeLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun LoginEntry(
  navController: NavController,
  viewModel: LoginViewModel
) {

  viewModel.observeLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)
  val state by viewModel.collectState().collectAsStateWithLifecycle()

  LoginScreen(navController = navController, viewModel = viewModel, state = state)
}