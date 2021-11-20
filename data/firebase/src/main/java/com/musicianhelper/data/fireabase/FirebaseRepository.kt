package com.musicianhelper.data.fireabase

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
  private val firebaseAuth: FirebaseAuth
) : LoginRepository {

  override suspend fun login(loginData: LoginData) {
    firebaseAuth.signInWithEmailAndPassword(loginData.name, loginData.password)
      .addOnSuccessListener {

      }
  }

  override fun logOut() {
    firebaseAuth.signOut()
  }
}
