package com.musicianhelper.registration.impl.data

import com.musicianhelper.registration.impl.domain.repositories.RegistrationRepository
import com.musicianhelper.core.common.data.User
import com.musicianhelper.core.common.data.UserData
import com.musicianhelper.core.common.domain.AuthenticationService
import com.musicianhelper.data.api.UserCreator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
  private val authService: AuthenticationService,
  private val userCreator: UserCreator
) : RegistrationRepository {

  override fun signIn(userData: UserData): Flow<Result<User>> {
    return authService.singIn(userData)
      .onEach {
        if (it.isSuccess) {
          saveUser(it.getOrNull()!!)
        }
      }
  }

  private fun saveUser(user: User) {
    userCreator.createUser(user)
  }
}