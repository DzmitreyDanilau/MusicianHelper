package com.musicianhelper.login.api

import com.musicianhelper.core.navigation.AggregateFeatureEntry

abstract class LoginEntry : AggregateFeatureEntry {

    final override val featureRoute = "login"

    val startDestination = featureRoute
}
