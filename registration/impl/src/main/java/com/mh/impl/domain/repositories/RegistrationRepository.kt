package com.mh.impl.domain.repositories

import com.musicianhelper.data.User
import com.musicianhelper.data.UserData
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {

  fun signIn(userData: UserData): Flow<Result<User>>
}
