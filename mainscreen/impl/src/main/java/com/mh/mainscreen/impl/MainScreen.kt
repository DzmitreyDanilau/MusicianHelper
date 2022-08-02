package com.mh.mainscreen.impl

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mh.mainscreen.impl.BottomNavigation

@Composable
fun MainScreen(navController: NavController) {
  Scaffold(
    bottomBar = { BottomNavigation(navController = navController) }
  ) {
  }
}