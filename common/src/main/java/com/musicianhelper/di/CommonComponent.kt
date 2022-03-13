package com.musicianhelper.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DispatchersModule::class])
interface CommonComponent : CommonProvider {

  @Component.Factory
  interface Factory {

    fun create(@BindsInstance context: Context): CommonComponent
  }
}
