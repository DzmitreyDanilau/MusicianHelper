package com.musicianhelper.data.fireabase.di

import com.musicianhelper.data.api.AuthenticationServiceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
  modules = [AuthServiceModule::class]
)
interface UserDataComponent : AuthenticationServiceProvider
