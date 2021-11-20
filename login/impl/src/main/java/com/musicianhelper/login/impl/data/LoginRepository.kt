package com.musicianhelper.login.impl.data

import com.musicianhelper.domain.UserDataSource
import javax.inject.Inject

class LoginRepository @Inject constructor(
  private val userDataSource: UserDataSource
) : Repository {
}