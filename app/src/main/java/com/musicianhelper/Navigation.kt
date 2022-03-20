package com.musicianhelper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.musicianhelper.di.LocalAppProvider
import com.musicianhelper.login.api.LoginEntry

@Composable
fun Navigation() {
  val navController = rememberNavController()
  val destinations = LocalAppProvider.current.destinations

  val loginScreen = destinations.find<LoginEntry>()

  Box(Modifier.fillMaxSize()) {
    NavHost(navController, startDestination = loginScreen.loginDestination()) {
      with(loginScreen) {
        navigation(navController, destinations)
      }
    }
  }
}