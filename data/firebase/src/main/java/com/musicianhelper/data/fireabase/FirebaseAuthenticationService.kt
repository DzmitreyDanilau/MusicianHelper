package com.musicianhelper.data.fireabase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.musicianhelper.data.User
import com.musicianhelper.di.IO
import com.musicianhelper.domain.AuthenticationService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthenticationService @Inject constructor(
        private val errorMapper: FirebaseAuthExceptionsMapper,
        private val firebaseAuth: FirebaseAuth,
        @IO private val dispatcher: CoroutineDispatcher,
) : AuthenticationService {

    override fun login(
            name: String,
            password: String
    ): Flow<Result<User>> {
        return flow<Result<User>> {
            val authResult = firebaseAuth.signInWithEmailAndPassword(name, password).await()
            emit(Result.success(mapToUser(authResult)))
        }.catch { e -> emit(Result.failure(errorMapper.map(e as FirebaseAuthException))) }
    }

    override fun logOut() {
        firebaseAuth.signOut()
    }

    private fun mapToUser(authResult: AuthResult): User {
        val user = authResult.user
        return User(id = user?.uid ?: "", name = user?.email ?: "")
    }
}
