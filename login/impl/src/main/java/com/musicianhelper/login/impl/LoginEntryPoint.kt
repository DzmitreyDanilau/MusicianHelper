package com.musicianhelper.login.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.musicianhelper.Destinations
import com.musicianhelper.data.api.LocalAuthenticationServiceProvider
import com.musicianhelper.di.LocalCommonProvider
import com.musicianhelper.di.injectedViewModel
import com.musicianhelper.login.api.LoginEntry
import com.musicianhelper.login.impl.di.DaggerLoginComponent
import com.musicianhelper.login.impl.ui.LoginScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class LoginEntryPoint @Inject constructor() : LoginEntry() {

    override fun NavGraphBuilder.navigation(
            navController: NavHostController,
            destinations: Destinations
    ) {
        composable(route = featureRoute) {
            val viewModel = injectedViewModel {
                DaggerLoginComponent
                        .builder()
                        .commonProvider(LocalCommonProvider.current)
                        .authenticationServiceProvider(LocalAuthenticationServiceProvider.current)
                        .build()
                        .viewModel
            }
            LoginScreen(navController = navController, viewModel = viewModel)
        }
    }
}
