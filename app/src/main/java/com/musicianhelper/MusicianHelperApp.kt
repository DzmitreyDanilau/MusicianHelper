package com.musicianhelper

import android.app.Application
import com.musicianhelper.data.fireabase.di.DaggerUserDataComponent
import com.musicianhelper.di.AppProvider
import com.musicianhelper.di.DaggerAppComponent
import com.musicianhelper.di.DaggerCommonComponent
import timber.log.Timber

class MusicianHelperApp : Application() {

    private val test: String = ""
    private val test1: String = ""
    private val test2: String = ""
    lateinit var appProvider: AppProvider

    override fun onCreate() {
        super.onCreate()

        val commonProvider = DaggerCommonComponent.factory().create(this)
        appProvider = DaggerAppComponent.builder()
            .commonProvider(commonProvider)
            .authenticationServiceProvider(DaggerUserDataComponent.builder().build())
            .build()

        Timber.plant(Timber.DebugTree())
    }
}

val Application.appProvider: AppProvider
    get() = (this as MusicianHelperApp).appProvider
