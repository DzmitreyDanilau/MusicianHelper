package com.musicianhelper.login.api

import com.musicianhelper.AggregateFeatureEntry

abstract class LoginEntry : AggregateFeatureEntry {

  final override val featureRoute = "login"

  fun loginDestination() = "login"
}
