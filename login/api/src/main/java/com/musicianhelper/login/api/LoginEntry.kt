package com.musicianhelper.login.api

import com.musicianhelper.ComposableFeatureEntry

abstract class LoginEntry : ComposableFeatureEntry {

  final override val featureRoute = "login"

  fun destination() = featureRoute
}
