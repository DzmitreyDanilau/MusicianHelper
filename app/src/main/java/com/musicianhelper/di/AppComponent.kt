package com.musicianhelper.di

import com.musicianhelper.data.api.UserDataSourceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [NavigationModule::class],
  dependencies = [CommonProvider::class, UserDataSourceProvider::class]
)
interface AppComponent : AppProvider
