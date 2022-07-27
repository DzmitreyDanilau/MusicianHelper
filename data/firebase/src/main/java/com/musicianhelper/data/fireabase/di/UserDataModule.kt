package com.musicianhelper.data.fireabase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.musicianhelper.data.ExceptionMapper
import com.musicianhelper.data.api.UserCreator
import com.musicianhelper.data.api.UserProvider
import com.musicianhelper.data.api.UserUpdater
import com.musicianhelper.data.fireabase.FirebaseAuthExceptionsMapper
import com.musicianhelper.data.fireabase.FirebaseAuthenticationService
import com.musicianhelper.data.fireabase.FirebaseUserDataSource
import com.musicianhelper.domain.AuthenticationService
import dagger.Binds
import dagger.Module
import dagger.Provides

// TODO think about how we can separate into FirebaseAuth and Firebase, to have the ability
// to use data source, in-app msgs etc

@Module(includes = [FirebaseModule::class, FirebaseAuthExceptionModule::class])
interface AuthServiceModule {

  @Binds
  fun bindAuthentificationService(
    firebaseAuth: FirebaseAuthenticationService
  ): AuthenticationService
}

@Module(includes = [FirebaseUserDataSourceModule::class])
interface UserDataSource {

  @Binds
  fun bindUserUpdaterStore(firestore: FirebaseUserDataSource): UserUpdater

  @Binds
  fun bindUserCreatorStore(firestore: FirebaseUserDataSource): UserCreator

  @Binds
  fun bindUserProviderStore(firestore: FirebaseUserDataSource): UserProvider
}

@Module(includes = [FirebaseModule::class])
object FirebaseUserDataSourceModule {

  @Provides
  fun provideUserDataSource(firestore: FirebaseFirestore): FirebaseUserDataSource {
    return FirebaseUserDataSource(firestore)
  }
}

@Module
object FirebaseModule {

  @Provides
  fun provideFirebaseAuth() = FirebaseAuth.getInstance()

  @Provides
  fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore
}

@Module
object FirebaseAuthExceptionModule {

  @Provides
  fun provideErrorMapper(): ExceptionMapper<FirebaseAuthException> =
    FirebaseAuthExceptionsMapper()
}
