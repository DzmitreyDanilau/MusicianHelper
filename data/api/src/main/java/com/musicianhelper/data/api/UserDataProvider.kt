package com.musicianhelper.data.api

import androidx.compose.runtime.compositionLocalOf
import com.musicianhelper.domain.LoginRepository
import com.musicianhelper.domain.UserDataStore

interface UserDataProvider {

  val userDataStore: UserDataStore

  val loginRepository: LoginRepository
}

val LocalUserDataProvider = compositionLocalOf<UserDataProvider> { error("No data provider found!") }
