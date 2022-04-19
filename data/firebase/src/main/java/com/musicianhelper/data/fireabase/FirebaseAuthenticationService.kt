package com.musicianhelper.data.fireabase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.musicianhelper.data.ExceptionMapper
import com.musicianhelper.data.User
import com.musicianhelper.domain.AuthenticationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthenticationService @Inject constructor(
    private val errorMapper: FirebaseAuthExceptionsMapper,
    private val firebaseAuth: FirebaseAuth
) : AuthenticationService {

    override fun login(
        name: String,
        password: String
    ): Flow<Result<User>> {
        return flow<Result<User>> {
            try {
                val authResult = firebaseAuth.signInWithEmailAndPassword(name, password).await()
                emit(Result.success(mapToUser(authResult)))
            } catch (e: Exception) {
                emit(Result.failure(errorMapper.map(e as FirebaseAuthException)))
            }
        }
    }

    override fun logOut() {
        TODO("Not yet implemented")
    }

    private fun mapToUser(authResult: AuthResult): User {
        val user = authResult.user
        return User(id = user?.uid ?: "", name = user?.email ?: "")
    }
}
