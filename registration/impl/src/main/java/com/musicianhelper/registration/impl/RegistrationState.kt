package com.musicianhelper.registration.impl

import com.musicianhelper.core.common.State

sealed interface RegistrationState : State {
  data class Default(val itemsList: List<ViewModel>) : RegistrationState
  object ShowPhotoSource : RegistrationState
}

interface ViewModel {
  val id: String
}
