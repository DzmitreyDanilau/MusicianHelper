package com.musicianhelper

import androidx.navigation.NavController
import com.musicianhelper.common.Navigation
import kotlinx.coroutines.flow.MutableSharedFlow

class AuthNavigator(
  private val navController: NavController
) : NavigatorEmitter {

  private val navigationFlow = MutableSharedFlow<Navigation>(extraBufferCapacity = 1)

  override fun navigateTo(destination: String) {
    TODO("Not yet implemented")
  }
}