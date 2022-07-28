package com.mh.impl.di

import com.mh.impl.domain.RegisterUseCase
import com.mh.impl.domain.RegistrationAction
import com.mh.impl.domain.RegistrationResult
import com.musicianhelper.domain.UseCase
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {

  @Binds
  fun bindRegistrationUseCase(
    useCase: RegisterUseCase
  ): UseCase<RegistrationAction, RegistrationResult>
}
