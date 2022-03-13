package com.musicianhelper.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object DispatchersModule {

  @Main
  @Provides
  fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

  @Default
  @Provides
  fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

  @IO
  @Provides
  fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}

@Qualifier
annotation class Main

@Qualifier
annotation class Default

@Qualifier
annotation class IO
