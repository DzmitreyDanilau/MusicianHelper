package com.musicianhelper.data.api

import androidx.compose.runtime.compositionLocalOf

interface UserDataSourceProvider {
  val userCreator: UserCreator
  val userUpdater: UserUpdater
  val userProvider: UserProvider
}

val LocalUserDataSourceProvider =
  compositionLocalOf<UserDataSourceProvider> { error("No data provider found!") }
