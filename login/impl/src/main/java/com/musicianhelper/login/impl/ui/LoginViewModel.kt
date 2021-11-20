package com.musicianhelper.login.impl.ui

import androidx.lifecycle.ViewModel
import com.musicianhelper.common.Store
import com.musicianhelper.login.impl.usecase.LoginUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoginViewModel @Inject constructor(
  private val login: LoginUseCase
) : ViewModel(), Store<LoginState, LoginAction> {

  override fun observeState(): StateFlow<LoginState> {
    TODO("Not yet implemented")
  }

  override fun dispatch(action: LoginAction) {
    TODO("Not yet implemented")
  }
}


