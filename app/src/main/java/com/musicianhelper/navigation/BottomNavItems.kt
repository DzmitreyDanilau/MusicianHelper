package com.musicianhelper.navigation

import com.musicianhelper.R

sealed class BottomNavItems(
    var title: String,
    var icon: Int,
    var route: String
) {
    object Home : BottomNavItems("Home", icon = R.drawable.ic_home, "HOME_ROTE")
    object Map : BottomNavItems("Map", icon = R.drawable.ic_map, "MAP_ROTE")
    object Profile : BottomNavItems("Profile", icon = R.drawable.ic_profile, "Profile_ROTE")
}