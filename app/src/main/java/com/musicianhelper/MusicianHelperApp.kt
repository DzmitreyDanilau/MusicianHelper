package com.musicianhelper

import android.app.Application
import com.musicianhelper.data.fireabase.di.DaggerUserAuthComponent
import com.musicianhelper.data.fireabase.di.DaggerUserDataSourceComponent
import com.musicianhelper.di.AppProvider
import com.musicianhelper.di.DaggerAppComponent
import com.musicianhelper.di.DaggerCommonComponent
import timber.log.Timber

class MusicianHelperApp : Application() {

  lateinit var appProvider: AppProvider

  override fun onCreate() {
    super.onCreate()

    val commonProvider = DaggerCommonComponent.factory().create(this)
    appProvider = DaggerAppComponent.builder()
      .commonProvider(commonProvider)
      .authenticationServiceProvider(DaggerUserAuthComponent.builder().build())
      .userDataSourceProvider(DaggerUserDataSourceComponent.builder().build())
      .build()

    Timber.plant(Timber.DebugTree())
  }
}

val Application.appProvider: AppProvider
  get() = (this as MusicianHelperApp).appProvider
