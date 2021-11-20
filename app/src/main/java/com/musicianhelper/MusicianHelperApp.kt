package com.musicianhelper

import android.app.Application
import com.musicianhelper.di.ComponentDependenciesProvider
import com.musicianhelper.di.HasComponentDependencies

class MusicianHelperApp : Application(), HasComponentDependencies {

  override val dependencies: ComponentDependenciesProvider
    get() = TODO("Not yet implemented")

  // private fun initializeComponent(): AppComponent {
  //   // Creates an instance of AppComponent using its Factory constructor
  //   // We pass the applicationContext that will be used as Context in the graph
  //   return DaggerAppComponent.factory().create(applicationContext)
  // }
}
