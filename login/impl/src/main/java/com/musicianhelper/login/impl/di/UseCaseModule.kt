package com.musicianhelper.login.impl.di

import com.musicianhelper.login.impl.ui.LoginUseCase
import com.musicianhelper.login.impl.usecase.Login
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

  @Binds
  fun bindLoginUseCase(useCase: LoginUseCase): Login
}
