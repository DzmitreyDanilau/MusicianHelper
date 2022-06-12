package com.mh.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.mh.registration.api.RegistrationEntry
import com.musicianhelper.Destinations
import com.musicianhelper.data.api.LocalAuthenticationServiceProvider
import com.musicianhelper.di.LocalCommonProvider
import com.musicianhelper.di.injectedViewModel
import javax.inject.Inject

class RegistrationEntryPoint @Inject constructor() : RegistrationEntry() {

    override fun NavGraphBuilder.navigation(
            navController: NavHostController,
            destinations: Destinations
    ) {
        composable(route = "@registration") {
//            val viewModel = injectedViewModel {
//                DaggerRegistrationComponent
//                        .builder()
//                        .commonProvider(LocalCommonProvider.current)
//                        .authenticationServiceProvider(LocalAuthenticationServiceProvider.current)
//                        .build()
//                        .viewModel
//            }
//            RegistrationScreen(navController = navController, viewModel = viewModel)
        }
    }
}
