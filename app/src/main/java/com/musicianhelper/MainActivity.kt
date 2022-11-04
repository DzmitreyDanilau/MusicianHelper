package com.musicianhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.musicianhelper.core.common.di.LocalCommonProvider
import com.musicianhelper.core.designsystem.theme.AppTheme
import com.musicianhelper.core.designsystem.theme.spacing
import com.musicianhelper.data.api.LocalAuthenticationServiceProvider
import com.musicianhelper.data.api.LocalUserDataSourceProvider
import com.musicianhelper.di.LocalAppProvider

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
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
