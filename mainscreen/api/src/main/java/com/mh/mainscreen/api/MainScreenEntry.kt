package com.mh.mainscreen.api

import com.musicianhelper.AggregateFeatureEntry

abstract class MainScreenEntry : AggregateFeatureEntry {

  final override val featureRoute = "main-screen"

  val startDestination = featureRoute
}
