package com.musicianhelper.data.fireabase.di

import com.musicianhelper.data.api.AuthenticationServiceProvider
import com.musicianhelper.di.DispatchersModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AuthServiceModule::class, DispatchersModule::class]
)
interface UserDataComponent : AuthenticationServiceProvider
