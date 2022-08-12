package com.musicianhelper.data.firebase.di

import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.data.api.UserDataSourceProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthServiceModule::class])
interface UserAuthComponent : AuthenticationServiceProvider

@Singleton
@Component(modules = [UserDataSource::class])
interface UserDataSourceComponent : UserDataSourceProvider
