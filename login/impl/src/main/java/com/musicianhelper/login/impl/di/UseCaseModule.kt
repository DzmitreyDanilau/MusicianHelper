package com.musicianhelper.login.impl.di

import com.musicianhelper.login.impl.domain.Login
import com.musicianhelper.login.impl.domain.LoginResult
import com.musicianhelper.login.impl.ui.LoginAction
import com.musicianhelper.login.impl.ui.LoginUseCase
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

  @Binds
  fun bindLoginUseCase(useCase: LoginUseCase): Login<LoginAction, LoginResult>
}
