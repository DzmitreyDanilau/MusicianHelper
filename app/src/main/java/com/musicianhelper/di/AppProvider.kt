package com.musicianhelper.di

import androidx.compose.runtime.compositionLocalOf
import com.musicianhelper.Destinations
import com.musicianhelper.data.api.AuthenticationServiceProvider

interface AppProvider : CommonProvider, AuthenticationServiceProvider {

  val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
