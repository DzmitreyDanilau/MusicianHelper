package com.mh.impl

import com.musicianhelper.common.State

sealed class RegistrationState : State {

    object Initial : RegistrationState()
}