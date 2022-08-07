package com.musicianhelper.core.common.domain

import com.musicianhelper.core.common.data.User
import com.musicianhelper.core.common.data.UserData
import kotlinx.coroutines.flow.Flow

interface AuthenticationService {

  fun login(name: String, password: String): Flow<Result<User>>

  fun logOut()

  fun singIn(userData: UserData) : Flow<Result<User>>
}
