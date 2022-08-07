package com.musicianhelper.main.screen.impl

sealed class BottomNavigationItem(
  val title: String,
  var route: String,
  var iconResId: Int
) {
  object Home : BottomNavigationItem("Home", "home", R.drawable.ic_home)
  object Profile : BottomNavigationItem("Profile", "user_profile", R.drawable.ic_user)
  object Map : BottomNavigationItem("Map", "map", R.drawable.ic_map)
}
