package com.musicianhelper.core.common.data

data class AuthThrowable(
    val errorText: String,
    val code: Int
) : Throwable()
