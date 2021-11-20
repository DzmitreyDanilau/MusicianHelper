package com.musicianhelper.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  dependencies = [CommonProvider::class],
  modules = [NavigationModule::class]
)
interface AppComponent : AppProvider
