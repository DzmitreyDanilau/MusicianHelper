package com.musicianhelper.data.fireabase

import com.google.firebase.firestore.FirebaseFirestore
import com.musicianhelper.data.User
import com.musicianhelper.data.api.UserCreator
import com.musicianhelper.data.api.UserProvider
import com.musicianhelper.data.api.UserUpdater
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class FirebaseUserDataSource(
  private val userDataStore: FirebaseFirestore
) : UserCreator, UserProvider, UserUpdater {

  override fun getUser(): Flow<User> {
    TODO("Not yet implemented")
  }

  override fun deleteUser() {
    TODO("Not yet implemented")
  }

  override fun editUser() {
    TODO("Not yet implemented")
  }

  override fun createUser() {
    Timber.tag("TEST").d("USERCREATED")
  }
}
