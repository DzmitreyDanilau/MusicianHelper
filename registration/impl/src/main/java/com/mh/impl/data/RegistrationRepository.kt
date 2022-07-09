package com.mh.impl.data

import com.mh.impl.domain.Repository
import com.musicianhelper.data.User
import com.musicianhelper.data.UserData
import com.musicianhelper.domain.AuthenticationService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
  private val authService: AuthenticationService
) : Repository {

  override fun signIn(userData: UserData): Flow<Result<User>> {
   return authService.singIn(userData)
  }
}