package com.mh.impl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun RegistrationScreen(
  navController: NavController,
  viewModel: RegistrationViewModel
) {

  val coroutineScope = rememberCoroutineScope()

  val state by viewModel.collectState().collectAsState()

  Box(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Image(
      imageVector = ImageVector.vectorResource(id = R.drawable.ic_add_photo),
      contentDescription = "",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .wrapContentSize()
        .padding(50.dp)
        .clickable(enabled = true, onClick = { viewModel.dispatch(ShowPickSourceDialogEvent) }),
    )
  }

  LaunchedEffect(coroutineScope) {

  }
}