package com.musicianhelper.login.impl.login.domain

import com.musicianhelper.data.User
import kotlinx.coroutines.flow.Flow

interface Repository {

  fun login(name: String, password: String): Flow<Result<User>>
}
