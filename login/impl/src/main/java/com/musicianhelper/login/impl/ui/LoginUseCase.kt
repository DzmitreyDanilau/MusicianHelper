package com.musicianhelper.login.impl.ui

import com.musicianhelper.login.impl.domain.Login
import com.musicianhelper.login.impl.domain.LoginResult
import com.musicianhelper.login.impl.domain.LoginResult.Fail
import com.musicianhelper.login.impl.domain.LoginResult.Success
import com.musicianhelper.login.impl.domain.Repository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@OptIn(FlowPreview::class)
class LoginUseCase @Inject constructor(
  private val repository: Repository
) : Login<LoginAction, LoginResult> {

  override fun apply(upstream: Flow<LoginAction>): Flow<LoginResult> {
    Timber.tag("LoginUseCase").d("LoginUseCase")
    return upstream.flatMapConcat {
      repository.login(it.name, it.password).map { result ->
        result.fold(
          onSuccess = { user -> Success(user) },
          onFailure = { error -> Fail(error) }
        )
      }
    }
  }
}