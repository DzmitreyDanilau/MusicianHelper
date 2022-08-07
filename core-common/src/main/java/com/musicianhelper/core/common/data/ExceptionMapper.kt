package com.musicianhelper.core.common.data

interface ExceptionMapper<in T : Exception> {

    fun map(exception: T): Throwable
}
