package com.mh.impl.di

import com.mh.impl.RegistrationEntryPoint
import com.mh.registration.api.RegistrationEntry
import com.musicianhelper.FeatureEntry
import com.musicianhelper.di.FeatureEntryKey
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
