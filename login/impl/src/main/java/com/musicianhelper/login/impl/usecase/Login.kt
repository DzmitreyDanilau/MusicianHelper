package com.musicianhelper.login.impl.usecase

import android.service.autofill.UserData
import kotlinx.coroutines.flow.Flow

interface Login {

  operator fun invoke(query: String): Flow<UserData>
}
