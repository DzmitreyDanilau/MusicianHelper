package com.musicianhelper.data

interface ExceptionMapper<in T : Exception> {

    fun map(exception: T): Throwable
}
