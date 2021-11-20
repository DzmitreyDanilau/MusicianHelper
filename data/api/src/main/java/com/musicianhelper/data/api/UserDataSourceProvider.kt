package com.musicianhelper.data.api

import androidx.compose.runtime.compositionLocalOf
import com.musicianhelper.domain.UserDataSource

interface UserDataSourceProvider {

  val userDataSource: UserDataSource
}

val LocalUserDataSourceProvider =
  compositionLocalOf<UserDataSourceProvider> { error("No data provider found!") }
