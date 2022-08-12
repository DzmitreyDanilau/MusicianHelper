package com.musicianhelper.registration.impl.di

import com.musicianhelper.registration.impl.data.RegistrationRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

  @Binds
  fun bindRepository(
    repository: RegistrationRepository
  ): com.musicianhelper.registration.impl.domain.repositories.RegistrationRepository
}
