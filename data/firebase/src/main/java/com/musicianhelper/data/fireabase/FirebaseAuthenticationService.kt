package com.musicianhelper.data.fireabase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.musicianhelper.data.User
import com.musicianhelper.data.UserData
import com.musicianhelper.domain.AuthenticationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class FirebaseAuthenticationService @Inject constructor(
  private val errorMapper: FirebaseAuthExceptionsMapper,
  private val firebaseAuth: FirebaseAuth
) : AuthenticationService {

  override fun login(
    name: String,
    password: String
  ): Flow<Result<User>> {
    return flow {
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

  override fun singIn(userData: UserData): Flow<Result<User>> {
    return flow {
      try {
        Timber.tag("TEST").d("SIGNIN name: ${userData.name}, password: ${userData.password}")
        val singInResult = firebaseAuth.createUserWithEmailAndPassword(
          userData.email, userData.password
        ).await()
        emit(Result.success(mapToUser(singInResult.user)))
      } catch (e: Exception) {
        emit(Result.failure(errorMapper.map(e as FirebaseAuthException)))
      }
    }
  }

  private fun mapToUser(authResult: AuthResult): User {
    val user = authResult.user
    return User(id = user?.uid ?: "", name = user?.email ?: "")
  }

  private fun mapToUser(firebaseUser: FirebaseUser?): User {
    return User(id = firebaseUser?.uid ?: "", name = firebaseUser?.email ?: "")
  }
}
