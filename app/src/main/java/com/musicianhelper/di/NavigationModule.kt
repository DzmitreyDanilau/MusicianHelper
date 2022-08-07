package com.musicianhelper.di

import com.musicianhelper.login.impl.di.LoginEntryModule
import com.musicianhelper.main.screen.impl.di.MainScreenEntryModule
import com.musicianhelper.registration.impl.di.RegistrationEntryModule
import dagger.Module

@Module(
  includes = [
    LoginEntryModule::class,
    RegistrationEntryModule::class,
    MainScreenEntryModule::class
  ]
)
interface NavigationModule
