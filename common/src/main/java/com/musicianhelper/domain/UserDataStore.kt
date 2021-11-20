package com.musicianhelper.domain

import kotlinx.coroutines.flow.Flow

interface UserDataStore {

  fun getUser(): Flow<User>
}

data class User(val id: String)