package com.musicianhelper.login.impl.di

import com.musicianhelper.FeatureEntry
import com.musicianhelper.di.FeatureEntryKey
import com.musicianhelper.login.api.LoginEntry
import com.musicianhelper.login.impl.LoginEntryPoint
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface LoginEntryModule {

  @Binds
  @Singleton
  @IntoMap
  @FeatureEntryKey(LoginEntry::class)
  fun bindLoginEntryPoint(entry: LoginEntryPoint): FeatureEntry
}
