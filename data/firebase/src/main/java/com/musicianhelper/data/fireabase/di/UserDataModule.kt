package com.musicianhelper.data.fireabase.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.musicianhelper.data.ExceptionMapper
import com.musicianhelper.data.fireabase.FirebaseAuthExceptionsMapper
import com.musicianhelper.data.fireabase.FirebaseAuthenticationService
import com.musicianhelper.domain.AuthenticationService
import dagger.Binds
import dagger.Module
import dagger.Provides

// TODO think about how we can separate into FirebaseAuth and Firebase, to have the ability
// to use data source, in-app msgs etc

@Module(includes = [FirebaseModule::class, FirebaseAuthExceptionModule::class])
interface AuthServiceModule {

    @Binds
    fun bindAuthentificationService(firebaseAuth: FirebaseAuthenticationService): AuthenticationService
}

@Module
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}

@Module
object FirebaseAuthExceptionModule {

    @Provides
    fun provideErrorMapper(): ExceptionMapper<FirebaseAuthException> =
        FirebaseAuthExceptionsMapper()
}
