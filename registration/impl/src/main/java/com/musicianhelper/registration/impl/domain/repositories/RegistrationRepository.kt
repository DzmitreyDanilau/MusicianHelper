package com.musicianhelper.registration.impl.domain.repositories

import com.musicianhelper.core.common.data.User
import com.musicianhelper.core.common.data.UserData
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {

  fun signIn(userData: UserData): Flow<Result<User>>
}
