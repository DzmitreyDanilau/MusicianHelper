package com.musicianhelper.data.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.musicianhelper.core.common.data.User
import com.musicianhelper.core.common.data.UserData
import com.musicianhelper.core.common.domain.AuthenticationService
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
    return User(uid = user?.uid ?: "", name = user?.email ?: "")
  }

  private fun mapToUser(firebaseUser: FirebaseUser?): User {
    return User(uid = firebaseUser?.uid ?: "", name = firebaseUser?.email ?: "")
  }
}
