package com.musicianhelper.data.api

import com.musicianhelper.data.User
import kotlinx.coroutines.flow.Flow

interface UserProvider {
  fun getUser(): Flow<User>
}

interface UserUpdater {
  fun deleteUser()
  fun editUser()
}

interface UserCreator {
  fun createUser()
}