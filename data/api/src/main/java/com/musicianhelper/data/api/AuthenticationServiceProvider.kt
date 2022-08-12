package com.musicianhelper.data.api

import androidx.compose.runtime.compositionLocalOf
import com.musicianhelper.core.common.domain.AuthenticationService

interface AuthenticationServiceProvider {
  val authService: AuthenticationService
}

val LocalAuthenticationServiceProvider =
  compositionLocalOf<AuthenticationServiceProvider> { error("No auth provider found!") }