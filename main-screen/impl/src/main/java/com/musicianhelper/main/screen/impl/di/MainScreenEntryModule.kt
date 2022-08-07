package com.musicianhelper.main.screen.impl.di

import com.musicianhelper.main.screen.impl.MainScreenEntryPoint
import com.musicianhelper.core.navigation.FeatureEntry
import com.musicianhelper.core.navigation.FeatureEntryKey
import com.musicianhelper.main.screen.api.MainScreenEntry
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface MainScreenEntryModule {
  @Binds
  @Singleton
  @IntoMap
  @FeatureEntryKey(MainScreenEntry::class)
  fun bindMainScreenEntryPoint(entry: MainScreenEntryPoint): FeatureEntry
}
