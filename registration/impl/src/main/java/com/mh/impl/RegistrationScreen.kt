package com.mh.impl

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mh.impl.RegistrationState.ShowPhotoSource
import com.musicianhelper.common.android.observeLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@Composable
fun RegistrationScreen(
  navController: NavController,
  viewModel: RegistrationViewModel
) {

  viewModel.observeLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)

  var pictureUri by remember { mutableStateOf<Uri?>(null) }
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult(),
    onResult = {
      pictureUri = it.data?.data
      viewModel.dispatchEvent(PictureSelected)
    })

  val coroutineScope = rememberCoroutineScope()

  val state by viewModel.collectState().collectAsState()

  when (state) {
    is ShowPhotoSource -> {
      val intent =
        Intent(
          Intent.ACTION_OPEN_DOCUMENT,
          MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply {
          addCategory(Intent.CATEGORY_OPENABLE)
        }
      launcher.launch(intent)
    }
    else -> {}
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .padding(8.dp)
      .fillMaxSize(),
  ) {
    Image(
      painter = rememberAsyncImagePainter(model = pictureUri ?: R.drawable.ic_add_photo),
      contentDescription = "",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .wrapContentSize()
        .padding(50.dp)
        .clickable(
          enabled = true, onClick = { viewModel.dispatchEvent(ShowPickSourceDialogEvent) }),
    )
  }

  LaunchedEffect(coroutineScope) {

  }
}