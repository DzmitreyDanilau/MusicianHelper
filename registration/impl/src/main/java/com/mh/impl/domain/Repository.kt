package com.mh.impl.domain

import com.musicianhelper.data.User
import com.musicianhelper.data.UserData
import kotlinx.coroutines.flow.Flow

interface Repository {

  fun signIn(userData: UserData): Flow<Result<User>>
}
