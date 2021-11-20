package com.musicianhelper.login.impl.ui

import androidx.lifecycle.ViewModel
import com.musicianhelper.common.Store
import com.musicianhelper.login.impl.usecase.Login
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoginViewModel @Inject constructor(
  private val loginUseCase: Login
) : ViewModel(), Store<LoginState, LoginAction> {

  init {
    val test = "SRC"
  }

  override fun observeState(): StateFlow<LoginState> {
    TODO("Not yet implemented")
  }

  override fun dispatch(action: LoginAction) {
    TODO("Not yet implemented")
  }
}


