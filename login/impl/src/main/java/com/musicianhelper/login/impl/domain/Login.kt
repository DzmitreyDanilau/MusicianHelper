package com.musicianhelper.login.impl.domain

import kotlinx.coroutines.flow.Flow

interface Login {

  operator fun invoke(name: String, password: String): Flow<LoginResult>
}
