package com.musicianhelper.login.impl.domain

import com.musicianhelper.core.common.data.User
import kotlinx.coroutines.flow.Flow

interface Repository {

  fun login(
    name: String,
    password: String
  ): Flow<Result<User>>
}
