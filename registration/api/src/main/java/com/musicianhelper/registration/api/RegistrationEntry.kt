package com.musicianhelper.registration.api

import com.musicianhelper.core.navigation.AggregateFeatureEntry

abstract class RegistrationEntry : AggregateFeatureEntry {

  override val featureRoute: String = "registration"

  val startDestination = "registration"
}