package com.musicianhelper.main.screen.api

import com.musicianhelper.core.navigation.AggregateFeatureEntry

abstract class MainScreenEntry : AggregateFeatureEntry {

  final override val featureRoute = "main-screen"

  val startDestination = featureRoute
}
