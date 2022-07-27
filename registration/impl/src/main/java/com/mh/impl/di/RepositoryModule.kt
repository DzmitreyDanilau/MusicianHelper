package com.mh.impl.di

import com.mh.impl.data.RegistrationRepository
import com.musicianhelper.data.api.UserCreator
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

  @Binds
  fun bindRepository(
    repository: RegistrationRepository
  ): com.mh.impl.domain.repositories.RegistrationRepository
}
