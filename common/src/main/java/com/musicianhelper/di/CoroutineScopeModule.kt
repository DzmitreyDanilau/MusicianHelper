package com.musicianhelper.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object CoroutineScopeModule {

  @Provides
  @Singleton
  @MainScope
  fun provideApplicationScope(): CoroutineScope {
    return CoroutineScope(context = SupervisorJob() + CoroutineName("ApplicationScope"))
  }
}

@Qualifier
annotation class MainScope
