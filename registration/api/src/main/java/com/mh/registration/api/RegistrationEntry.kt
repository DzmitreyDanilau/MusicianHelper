package com.mh.registration.api

import com.musicianhelper.AggregateFeatureEntry

abstract class RegistrationEntry : AggregateFeatureEntry {

    override val featureRoute: String = "@registration"

    val startDestination = "registration"
}