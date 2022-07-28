package com.musicianhelper.login.impl.ui

import com.musicianhelper.common.Action

data class LoginAction(
  val name: String,
  val password: String
) : Action

object SignUpAction : Action
