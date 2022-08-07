package com.musicianhelper.registration.impl.di

import com.musicianhelper.core.navigation.FeatureEntry
import com.musicianhelper.core.navigation.FeatureEntryKey
import com.musicianhelper.registration.api.RegistrationEntry
import com.musicianhelper.registration.impl.RegistrationEntryPoint
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface RegistrationEntryModule {
  @Binds
  @Singleton
  @IntoMap
  @FeatureEntryKey(RegistrationEntry::class)
  fun bindRegistrationEntryPoint(entry: RegistrationEntryPoint): FeatureEntry
}
