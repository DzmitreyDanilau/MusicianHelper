package com.musicianhelper.core.common.di

import android.content.Context
import com.musicianhelper.core.common.DefaultResourceProvider
import com.musicianhelper.core.common.ResourceProvider
import dagger.Module
import dagger.Provides

@Module
object ResourceProviderModule {

  @Provides
  fun provideResourceProvider(context: Context): ResourceProvider {
    return DefaultResourceProvider(context = context)
  }
}
