package com.musicianhelper.domain

import com.musicianhelper.data.User
import com.musicianhelper.data.UserData
import kotlinx.coroutines.flow.Flow

interface AuthenticationService {

  fun login(name: String, password: String): Flow<Result<User>>

  fun logOut()

  fun singIn(userData: UserData) : Flow<Result<User>>
}
