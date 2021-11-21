package com.musicianhelper.login.impl.ui

import com.musicianhelper.common.android.BaseViewModel
import com.musicianhelper.login.impl.domain.Login
import com.musicianhelper.login.impl.domain.LoginResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class LoginViewModel @Inject constructor(
  private val loginUseCase: Login
) : BaseViewModel<LoginState, LoginEvent, LoginAction, LoginResult>(
  initialState = LoginState(progress = false, success = false)
) {

  override fun reduceState(previous: LoginState, result: LoginResult): LoginState {
    return when (result) {
      is LoginResult.Success -> LoginState(progress = false, success = true)
      is LoginResult.Fail -> LoginState(
        progress = false,
        success = false,
        error = result.error.message
      )
      is LoginResult.Loading -> LoginState(progress = true, success = false)
    }
  }

  override fun mapEventToAction(event: LoginEvent): LoginAction {
    return when (event) {
      is LoginEvent.Login -> LoginAction.Login(event.name, event.password)
    }
  }

  override fun mapActionToResult(action: LoginAction): Flow<LoginResult> {
    return when (action) {
      is LoginAction.Login -> loginUseCase.invoke(action.name, action.password)
    }
  }
}
