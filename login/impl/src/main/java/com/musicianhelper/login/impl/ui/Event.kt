package com.musicianhelper.login.impl.ui

import com.musicianhelper.common.Event

sealed class LoginEvent : Event {
  data class Login(val name: String, val password: String) : LoginEvent()
  object DismissSnackbar : LoginEvent()
}



