package com.musicianhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.musicianhelper.authentication.LoginScreen
import com.musicianhelper.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        // A surface container using the "background" color from the theme
        Surface(color = MaterialTheme.colors.background) {
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
