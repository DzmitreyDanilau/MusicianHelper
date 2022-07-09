package com.mh.impl.di

import com.mh.impl.data.RegistrationRepository
import com.mh.impl.domain.Repository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

  @Binds
  fun bindRepository(repository: RegistrationRepository): Repository
}
