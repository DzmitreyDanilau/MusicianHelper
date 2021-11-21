package com.musicianhelper.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
object DispatchersModule {

  @Main
  @Provides
  fun provideMainDispatcher() = Dispatchers.Main

  @Default
  @Provides
  fun provideDefaultDispatcher() = Dispatchers.Default

  @IO
  @Provides
  fun provideIODispatcher() = Dispatchers.IO
}

@Qualifier
annotation class Main

@Qualifier
annotation class Default

@Qualifier
annotation class IO
