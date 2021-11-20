package com.musicianhelper.login.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.musicianhelper.Destinations
import com.musicianhelper.login.api.LoginEntry
import javax.inject.Inject

class LoginEntryPoint @Inject constructor() : LoginEntry() {

  @Composable
  override fun NavGraphBuilder.Composable(
    navController: NavHostController,
    destinations: Destinations,
    backStackEntry: NavBackStackEntry
  ) {
    TODO("Not yet implemented")
  }
}