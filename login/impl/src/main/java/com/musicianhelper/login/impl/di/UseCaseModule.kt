package com.musicianhelper.login.impl.di

import com.musicianhelper.core.common.domain.UseCase
import com.musicianhelper.login.impl.domain.LoginResult
import com.musicianhelper.login.impl.domain.LoginUseCase
import com.musicianhelper.login.impl.ui.LoginAction
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

  @Binds
  fun bindLoginUseCase(useCase: LoginUseCase): UseCase<LoginAction, LoginResult>
}
