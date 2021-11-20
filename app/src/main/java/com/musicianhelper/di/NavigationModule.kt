package com.musicianhelper.di

import com.musicianhelper.login.impl.di.LoginEntryModule
import dagger.Module

@Module(includes = [LoginEntryModule::class])
interface NavigationModule
