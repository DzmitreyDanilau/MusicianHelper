package com.musicianhelper.registration.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import com.mh.impl.RegistrationScreen
import com.musicianhelper.core.common.observeLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun RegistrationRoute(
  navController: NavController,
  viewModel: RegistrationViewModel,
) {

  val state by viewModel.collectState().collectAsState()

  viewModel.observeLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)
  RegistrationScreen(navController, viewModel, state)
}
