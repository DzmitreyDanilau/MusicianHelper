package com.musicianhelper.data.firebase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.musicianhelper.core.common.data.ExceptionMapper
import com.musicianhelper.core.common.di.CoroutineScopeModule
import com.musicianhelper.core.common.di.DispatchersModule
import com.musicianhelper.core.common.di.IO
import com.musicianhelper.core.common.di.MainScope
import com.musicianhelper.core.common.domain.AuthenticationService
import com.musicianhelper.data.api.UserCreator
import com.musicianhelper.data.api.UserProvider
import com.musicianhelper.data.api.UserUpdater
import com.musicianhelper.data.firebase.FirebaseAuthExceptionsMapper
import com.musicianhelper.data.firebase.FirebaseAuthenticationService
import com.musicianhelper.data.firebase.FirebaseUserDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

// TODO think about how we can separate into FirebaseAuth and Firebase, to have the ability
// to use data source, in-app msgs etc

@Module(includes = [FirebaseModule::class, FirebaseAuthExceptionModule::class])
interface AuthServiceModule {

  @Binds
  @Singleton
  fun bindAuthentificationService(
    firebaseAuth: FirebaseAuthenticationService
  ): AuthenticationService
}

@Module(
  includes = [
    FirebaseUserDataSourceModule::class,
    CoroutineScopeModule::class,
    DispatchersModule::class
  ]
)
interface UserDataSource {

  @Binds
  fun bindUserUpdaterStore(firestore: FirebaseUserDataSource): UserUpdater

  @Binds
  fun bindUserCreatorStore(firestore: FirebaseUserDataSource): UserCreator

  @Binds
  fun bindUserProviderStore(firestore: FirebaseUserDataSource): UserProvider
}

@Module(
  includes = [FirebaseModule::class]
)
object FirebaseUserDataSourceModule {

  @Provides
  @Singleton
  fun provideUserDataSource(
    firestore: FirebaseFirestore,
    @IO dispatcher: CoroutineDispatcher,
    @MainScope coroutineScope: CoroutineScope
  ): FirebaseUserDataSource {
    return FirebaseUserDataSource(firestore, dispatcher, coroutineScope)
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
