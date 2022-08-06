package com.musicianhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import com.musicianhelper.data.api.LocalAuthenticationServiceProvider
import com.musicianhelper.data.api.LocalUserDataSourceProvider
import com.musicianhelper.di.LocalAppProvider
import com.musicianhelper.di.LocalCommonProvider
import com.musicianhelper.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
          CompositionLocalProvider(
            LocalAppProvider provides application.appProvider,
            LocalCommonProvider provides application.appProvider,
            LocalAuthenticationServiceProvider provides application.appProvider,
            LocalUserDataSourceProvider provides application.appProvider
          ) {
            Navigation()
          }
        }
      }
    }
  }
}
