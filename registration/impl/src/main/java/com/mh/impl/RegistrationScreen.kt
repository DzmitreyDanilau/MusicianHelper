package com.mh.impl

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(
        navController: NavController,
        viewModel: RegistrationViewModel
) {
    Box {
        Text(text = "HELLO YOU ARE IN REGISTRATION SCREEN")
    }
}