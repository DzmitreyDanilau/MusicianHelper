package com.musicianhelper.login.impl.ui

import com.musicianhelper.login.impl.domain.Login
import com.musicianhelper.login.impl.domain.LoginResult
import com.musicianhelper.login.impl.domain.LoginResult.Fail
import com.musicianhelper.login.impl.domain.LoginResult.NavigateToRegisterResult
import com.musicianhelper.login.impl.domain.LoginResult.Success
import com.musicianhelper.login.impl.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginUseCase @Inject constructor(
  private val repository: Repository
) : Login {

  override fun invoke(name: String, password: String): Flow<LoginResult> {
    return repository.login(name, password).map {
      it.fold(
        onSuccess = { user -> Success(user) },
        onFailure = { error -> NavigateToRegisterResult }
      )
    }
  }
}