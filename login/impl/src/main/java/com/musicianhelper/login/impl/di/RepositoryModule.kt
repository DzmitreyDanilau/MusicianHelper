package com.musicianhelper.login.impl.di

import com.musicianhelper.login.impl.data.LoginRepository
import com.musicianhelper.login.impl.domain.Repository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

  @Binds
  fun bindRepository(repository: LoginRepository) : Repository
}
