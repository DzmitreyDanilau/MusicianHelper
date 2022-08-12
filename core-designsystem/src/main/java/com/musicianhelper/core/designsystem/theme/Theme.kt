package com.musicianhelper.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
  primary = Purple500,
  primaryContainer = Purple700,
  secondary = Purple200
)

private val LightColorPalette = lightColorScheme(
  primary = Purple500,
  primaryContainer = Purple700,
  secondary = Purple200,
  surface = Color.White,
  background = Color.White,
  error = BaselineRed,
  onPrimary = Color.White,
  onSecondary = Color.Black

  /* Other default colors to override
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  CompositionLocalProvider(
    LocalSpacing provides Spacing()
  ) {
    MaterialTheme(
      colorScheme = colors,
      typography = Typography,
      shapes = Shapes,
      content = content
    )
  }
}
