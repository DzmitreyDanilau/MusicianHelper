package com.musicianhelper.domain

interface LoginRepository {

  suspend fun login(loginData: LoginData)

  fun logOut()
}

data class LoginData(val name: String, val password: String)
