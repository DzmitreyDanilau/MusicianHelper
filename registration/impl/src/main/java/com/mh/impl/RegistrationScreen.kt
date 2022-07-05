package com.mh.impl

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mh.impl.RegistrationState.ShowPhotoSource
import com.mh.ui.image.CircleBorderImage
import com.mh.ui.inputfields.DefaultOutlinedField
import com.mh.ui.list.ColumnTest
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

  val state by viewModel.collectState().collectAsState()

  when (state) {
    is ShowPhotoSource -> {
      val intent =
        Intent(
          Intent.ACTION_OPEN_DOCUMENT,
          MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).apply {
          addCategory(Intent.CATEGORY_OPENABLE)
          type = "image/*"
        }
      launcher.launch(intent)
    }
    else -> {}
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .padding(50.dp)
      .fillMaxSize(),
  ) {
    CircleBorderImage(
      isClickable = true,
      pictureUri = pictureUri,
      size = 100.dp,
      verticalPadding = 50.dp,
      borderWidth = 2.dp,
      borderColor = Color.Gray,
      defaultDrawable = R.drawable.ic_add_photo,
      onClick = { viewModel.dispatchEvent(ShowPickSourceDialogEvent) }
    )
    val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    ColumnTest(list = list) {
      ItemFactory(item = it)
    }
  }
}

@Composable
fun ItemFactory(item: Int) {
  when (item) {
    1 -> MySimpleListItem(item.toString())
    2 -> MySimpleListItem(item.toString())
    3 -> MySimpleListItem(item.toString())
    4 -> MySimpleListItem(item.toString())
    else -> Text(text = item.toString())
  }
}

@Composable
fun MySimpleListItem(text: String) {
  DefaultOutlinedField(
    value = "",
    onValueChange = { },
    label = "Email",
    icon = Icons.Default.Email,
  )
}
