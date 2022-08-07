package com.musicianhelper

import android.app.Application
import com.musicianhelper.core.common.di.DaggerCommonComponent
import com.musicianhelper.data.firebase.di.DaggerUserAuthComponent
import com.musicianhelper.data.firebase.di.DaggerUserDataSourceComponent
import com.musicianhelper.di.AppProvider
import com.musicianhelper.di.DaggerAppComponent

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
  }
}

val Application.appProvider: AppProvider
  get() = (this as MusicianHelperApp).appProvider
