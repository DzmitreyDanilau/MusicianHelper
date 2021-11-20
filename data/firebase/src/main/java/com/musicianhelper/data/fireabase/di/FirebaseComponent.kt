package com.musicianhelper.data.fireabase.di

import com.musicianhelper.data.api.UserDataSourceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [UserDataSourceModule::class],
  dependencies = []
)
interface FirebaseComponent : UserDataSourceProvider
