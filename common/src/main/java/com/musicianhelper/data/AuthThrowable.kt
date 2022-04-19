package com.musicianhelper.data

data class AuthThrowable(
    val errorText: String,
    val code: Int
) : Throwable()
