package com.musicianhelper.data.fireabase.di

import com.google.firebase.auth.FirebaseAuth
import com.musicianhelper.data.fireabase.FirebaseUserDataSource
import com.musicianhelper.domain.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides

// TODO think about how we can separate into FirebaseAuth and Firebase, to have the ability
// to use data source, in-app msgs etc

@Module(includes = [FirebaseModule::class])
interface UserDataSourceModule {

  @Binds
  fun bindUserDataSource(firebaseAuth: FirebaseUserDataSource): UserDataSource
}

@Module
object FirebaseModule {


  @Provides
  fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}