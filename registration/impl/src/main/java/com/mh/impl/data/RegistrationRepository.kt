package com.mh.impl.data

import com.mh.impl.domain.repositories.RegistrationRepository
import com.musicianhelper.data.User
import com.musicianhelper.data.UserData
import com.musicianhelper.data.api.UserCreator
import com.musicianhelper.domain.AuthenticationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
  private val authService: AuthenticationService,
  private val userCreator: UserCreator
) : RegistrationRepository {

  override fun signIn(userData: UserData): Flow<Result<User>> {
    return authService.singIn(userData)
      .onEach {
        Timber.tag("TEST").d("Sucess: ${it.isSuccess}")
        if(it.isSuccess) {
          saveUser(it.getOrNull()!!)
        }
      }
  }

  private fun saveUser(user: User) {
    Timber.tag("TEST").d("SAVE")
    userCreator.createUser(user)
  }
}