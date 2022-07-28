package com.musicianhelper.di

import android.content.Context
import com.musicianhelper.common.android.DefaultResourceProvider
import com.musicianhelper.common.android.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ResourceProviderModule {

  @Provides
  fun provideResourceProvider(context: Context): ResourceProvider {
    return DefaultResourceProvider(context = context)
  }
}
