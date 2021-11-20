package com.musicianhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.musicianhelper.login.impl.ui.LoginScreen
import com.musicianhelper.di.LocalCommonProvider
import com.musicianhelper.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        Surface(color = MaterialTheme.colors.background) {
          CompositionLocalProvider {
            LocalCommonProvider provides application.appProvider
          }
          Start()
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun Start() {
  LoginScreen()
}
