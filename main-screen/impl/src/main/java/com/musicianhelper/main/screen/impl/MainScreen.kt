package com.musicianhelper.main.screen.impl

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


// TODO CHECK experimental status
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
  Scaffold(
    bottomBar = { BottomNavigation(navController = navController) }
  ) {

  }
}