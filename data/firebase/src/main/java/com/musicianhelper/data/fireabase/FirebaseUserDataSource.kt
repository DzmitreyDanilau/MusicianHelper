package com.musicianhelper.data.fireabase

import com.google.firebase.auth.FirebaseAuth
import com.musicianhelper.data.User
import com.musicianhelper.domain.UserDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseUserDataSource @Inject constructor(
  private val firebaseAuth: FirebaseAuth
) : UserDataSource {

  override fun getUser(): Flow<User> {
    TODO("Not yet implemented")
  }
}
