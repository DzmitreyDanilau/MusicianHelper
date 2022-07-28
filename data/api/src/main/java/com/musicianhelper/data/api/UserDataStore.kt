package com.musicianhelper.data.api

import com.musicianhelper.data.User
import kotlinx.coroutines.flow.Flow

interface UserProvider {
  fun getUser(uid: String): Flow<Result<User?>>
}

interface UserUpdater {
  fun deleteUser()
  fun editUser(user: User) : Flow<Result<Boolean>>
}

interface UserCreator {
  fun createUser(user: User)
}