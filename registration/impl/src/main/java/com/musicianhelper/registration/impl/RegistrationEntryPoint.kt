package com.musicianhelper.registration.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.musicianhelper.core.common.di.LocalCommonProvider
import com.musicianhelper.core.common.di.injectedViewModel
import com.musicianhelper.core.navigation.Destinations
import com.musicianhelper.data.api.LocalAuthenticationServiceProvider
import com.musicianhelper.data.api.LocalUserDataSourceProvider
import com.musicianhelper.registration.api.RegistrationEntry
import com.musicianhelper.registration.impl.di.DaggerRegistrationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class RegistrationEntryPoint @Inject constructor() : RegistrationEntry() {

  override fun NavGraphBuilder.navigation(
    navController: NavHostController,
    destinations: Destinations
  ) {
    navigation(startDestination = startDestination, route = "@registration") {
      composable(route = featureRoute, content = {
        val common = LocalCommonProvider.current
        val auth = LocalAuthenticationServiceProvider.current
        val user = LocalUserDataSourceProvider.current
        val viewModel = injectedViewModel {
          DaggerRegistrationComponent
            .builder()
            .commonProvider(common)
            .authenticationServiceProvider(auth)
            .userDataSourceProvider(user)
            .build()
            .viewModel
        }
        RegistrationRoute(
          navController = navController,
          viewModel = viewModel,
        )
      })
    }
  }
}
