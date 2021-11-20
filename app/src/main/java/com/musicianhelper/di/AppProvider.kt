package com.musicianhelper.di

import androidx.compose.runtime.compositionLocalOf
import com.musicianhelper.Destinations

interface AppProvider : CommonProvider {

  val destinations: Destinations
}

val LocalAppProvider = compositionLocalOf<AppProvider> { error("No app provider found!") }
