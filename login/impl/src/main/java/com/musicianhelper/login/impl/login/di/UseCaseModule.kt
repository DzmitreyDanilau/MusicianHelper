package com.musicianhelper.login.impl.login.di

import com.musicianhelper.login.impl.login.ui.LoginUseCase
import com.musicianhelper.login.impl.login.domain.Login
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

  @Binds
  fun bindLoginUseCase(useCase: LoginUseCase): Login
}
