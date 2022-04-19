package com.musicianhelper

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.musicianhelper.di.LocalAppProvider
import com.musicianhelper.login.api.LoginEntry

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val destinations = LocalAppProvider.current.destinations

    val loginScreen = destinations.find<LoginEntry>()

    /**
     * Temporary as rout in the NavHost is LoginEntry route
     */

    NavHost(
        navController = navController,
        startDestination = loginScreen.startDestination,
        route = loginScreen.featureRoute
    ) {
        with(loginScreen) {
            navigation(navController, destinations)
        }
    }
}
