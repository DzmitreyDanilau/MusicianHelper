package com.musicianhelper.login.impl.ui

import android.service.autofill.UserData
import com.musicianhelper.login.impl.data.Repository
import com.musicianhelper.login.impl.usecase.Login
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
  private val repository: Repository
) : Login {

  init {
    val test = "DASDASD"
  }

  override fun invoke(query: String): Flow<UserData> {
    TODO("Not yet implemented")
  }
}
