package com.musicianhelper.core.common.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(
  modules = [
    DispatchersModule::class,
    ResourceProviderModule::class,
    CoroutineScopeModule::class
  ]
)
interface CommonComponent : CommonProvider {

  @Component.Factory
  interface Factory {

    fun create(@BindsInstance context: Context): CommonComponent
  }
}
