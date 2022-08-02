package com.musicianhelper.di

import com.mh.impl.di.RegistrationEntryModule
import com.mh.mainscreen.impl.di.MainScreenEntryModule
import com.musicianhelper.login.impl.di.LoginEntryModule
import dagger.Module

@Module(
  includes = [
    LoginEntryModule::class,
    RegistrationEntryModule::class,
    MainScreenEntryModule::class
  ]
)
interface NavigationModule
