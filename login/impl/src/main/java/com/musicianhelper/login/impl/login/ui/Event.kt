package com.musicianhelper.login.impl.login.ui

import com.musicianhelper.common.Event

sealed class LoginEvent : Event {
  data class Login(val name: String, val password: String) : LoginEvent()
  object SignUpClicked : LoginEvent()
  object DismissSnackbar : LoginEvent()
}



