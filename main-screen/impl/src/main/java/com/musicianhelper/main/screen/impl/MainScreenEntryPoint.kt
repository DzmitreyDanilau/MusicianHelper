package com.musicianhelper.main.screen.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.musicianhelper.core.navigation.Destinations
import com.musicianhelper.main.screen.api.MainScreenEntry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MainScreenEntryPoint @Inject constructor() : MainScreenEntry() {

  override fun NavGraphBuilder.navigation(
    navController: NavHostController,
    destinations: Destinations
  ) {
    composable(route = featureRoute) {
      MainRoute(navController)
    }
  }
}
