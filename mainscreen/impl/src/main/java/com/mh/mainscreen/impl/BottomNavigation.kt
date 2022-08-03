package com.mh.mainscreen.impl

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mh.mainscreen.impl.BottomNavigationItem.Home
import com.mh.mainscreen.impl.BottomNavigationItem.Map
import com.mh.mainscreen.impl.BottomNavigationItem.Profile

@Composable
fun BottomNavigation(navController: NavController) {
  val navItems = listOf(Home, Map, Profile)

  androidx.compose.material.BottomNavigation(
    modifier = Modifier
      .navigationBarsPadding()
      .height(56.dp),
  ) {
    navItems.forEach {
      BottomNavigationItem(
        icon = {
          Icon(
            painter = painterResource(it.iconResId),
            contentDescription = it.title
          )
        },
        selected = true,
        onClick = { /*TODO*/ }
      )
    }
  }
}

@Composable
fun AnimatableIcon(
  imageVector: ImageVector,
  modifier: Modifier = Modifier,
  iconSize: Dp = ICON_SIZE,
  scale: Float = 1f,
  color: Color = COLOR_NORMAL,
  onClick: () -> Unit
) {
  // Animation params
  val animatedScale: Float by animateFloatAsState(
    targetValue = scale,
    // Here the animation spec serves no purpose but to demonstrate in slow speed.
    animationSpec = TweenSpec(
      durationMillis = 2000,
      easing = FastOutSlowInEasing
    )
  )
  val animatedColor by animateColorAsState(
    targetValue = color,
    animationSpec = TweenSpec(
      durationMillis = 2000,
      easing = FastOutSlowInEasing
    )
  )

  IconButton(
    onClick = onClick,
    modifier = modifier.size(iconSize)
  ) {
    Icon(
      imageVector = imageVector,
      contentDescription = "dummy",
      tint = animatedColor,
      modifier = modifier.scale(animatedScale)
    )
  }
}

private val ICON_SIZE = 24.dp
private val COLOR_NORMAL = Color(0xffEDEFF4)
private val COLOR_SELECTED = Color(0xff496DE2)