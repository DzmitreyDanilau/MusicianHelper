package com.musicianhelper.data.fireabase.di

import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.data.api.UserDataSourceProvider
import dagger.Component

@Component(
  modules = [AuthServiceModule::class]
)
interface UserAuthComponent : AuthenticationServiceProvider

@Component(
  modules = [UserDataSource::class]
)
interface UserDataSourceComponent : UserDataSourceProvider