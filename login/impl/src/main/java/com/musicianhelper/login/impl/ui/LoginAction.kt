package com.musicianhelper.login.impl.ui

import com.musicianhelper.common.Action

sealed class LoginAction : Action {
  data class Login(val name: String, val password: String) : LoginAction()
  object SignUpAction : LoginAction()
}
