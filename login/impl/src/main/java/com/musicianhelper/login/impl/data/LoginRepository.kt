package com.musicianhelper.login.impl.data

import com.musicianhelper.data.User
import com.musicianhelper.domain.AuthenticationService
import com.musicianhelper.login.impl.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepository @Inject constructor(
  private val authService: AuthenticationService,
) : Repository {

  override fun login(name: String, password: String): Flow<Result<User>> {
    return authService.login(name, password)
  }
}
