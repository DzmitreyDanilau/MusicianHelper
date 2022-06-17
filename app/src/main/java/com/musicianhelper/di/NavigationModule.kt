package com.musicianhelper.di

import com.mh.impl.di.RegistrationEntryModule
import com.musicianhelper.login.impl.di.LoginEntryModule
import dagger.Module

@Module(
        includes = [
            LoginEntryModule::class,
            RegistrationEntryModule::class
        ]
)
interface NavigationModule
