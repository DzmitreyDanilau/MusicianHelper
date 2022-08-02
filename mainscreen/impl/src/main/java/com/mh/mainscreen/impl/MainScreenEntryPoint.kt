package com.mh.mainscreen.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mh.mainscreen.api.MainScreenEntry
import com.musicianhelper.Destinations
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
