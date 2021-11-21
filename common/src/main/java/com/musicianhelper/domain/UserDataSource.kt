package com.musicianhelper.domain

import com.musicianhelper.data.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

  fun getUser(): Flow<User>
}
