package com.musicianhelper.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.musicianhelper.core.common.data.User
import com.musicianhelper.core.common.di.IO
import com.musicianhelper.core.common.di.MainScope
import com.musicianhelper.data.api.UserCreator
import com.musicianhelper.data.api.UserProvider
import com.musicianhelper.data.api.UserUpdater
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FirebaseUserDataSource(
  private val firestore: FirebaseFirestore,
  @IO private val dispatcher: CoroutineDispatcher,
  @MainScope private val coroutineScope: CoroutineScope
) : UserCreator, UserProvider, UserUpdater {

  override fun getUser(uid: String): Flow<Result<User?>> {
    return flow {
      emit(
        Result.success(
          firestore.collection(userCollectionAlias)
            .document(uid)
            .get()
            .await()
            .toObject(User::class.java)
        )
      )
    }.catch {
      emit(Result.failure(it))
    }
  }

  override fun deleteUser() {
    TODO("Not yet implemented")
  }

  override fun editUser(user: User): Flow<Result<Boolean>> {
    return setUser(user)
  }

  override fun createUser(user: User) {
    coroutineScope.launch(dispatcher) {
      setUser(user).collect()
    }
  }

  private fun setUser(user: User): Flow<Result<Boolean>> {
    return flow<Result<Boolean>> {
      firestore.collection(userCollectionAlias).document(user.uid).set(user).await()
      emit(Result.success(true))
    }.catch {
      emit(Result.failure(it))
    }
  }
}
