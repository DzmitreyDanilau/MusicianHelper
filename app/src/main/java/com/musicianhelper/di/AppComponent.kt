package com.musicianhelper.di

import com.musicianhelper.data.api.AuthenticationServiceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [NavigationModule::class],
  dependencies = [
    CommonProvider::class,
    AuthenticationServiceProvider::class
  ]
)
interface AppComponent : AppProvider
