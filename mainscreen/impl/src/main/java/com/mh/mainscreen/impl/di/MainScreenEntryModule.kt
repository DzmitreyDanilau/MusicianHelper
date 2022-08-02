package com.mh.mainscreen.impl.di

import com.mh.mainscreen.api.MainScreenEntry
import com.mh.mainscreen.impl.MainScreenEntryPoint
import com.musicianhelper.FeatureEntry
import com.musicianhelper.di.FeatureEntryKey
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
