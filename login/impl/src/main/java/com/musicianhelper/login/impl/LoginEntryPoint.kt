package com.musicianhelper.login.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.musicianhelper.Destinations
import com.musicianhelper.data.api.LocalAuthenticationServiceProvider
import com.musicianhelper.login.api.LoginEntry
import com.musicianhelper.login.impl.di.DaggerLoginComponent
import com.musicianhelper.login.impl.ui.LoginScreen
import injectedViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LoginEntryPoint @Inject constructor() : LoginEntry() {

  @Composable
  override fun NavGraphBuilder.Composable(
    navController: NavHostController,
    destinations: Destinations,
    backStackEntry: NavBackStackEntry
  ) {

    val viewModel = injectedViewModel {
      DaggerLoginComponent
        .builder()
        .authenticationServiceProvider(LocalAuthenticationServiceProvider.current)
        .build()
        .viewModel
    }

    LoginScreen(viewModel = viewModel)
  }
}
