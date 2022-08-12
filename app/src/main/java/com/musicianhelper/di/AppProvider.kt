package com.musicianhelper.di

import androidx.compose.runtime.compositionLocalOf
import com.musicianhelper.core.common.di.CommonProvider
import com.musicianhelper.core.navigation.Destinations
import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.data.api.UserDataSourceProvider
import javax.inject.Singleton

@Singleton
interface AppProvider : CommonProvider, AuthenticationServiceProvider, UserDataSourceProvider {

  val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
