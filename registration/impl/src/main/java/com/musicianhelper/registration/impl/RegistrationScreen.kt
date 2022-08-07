package com.mh.impl

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore.Images.Media
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mh.impl.models.RegistrationImage
import com.mh.impl.models.RegistrationInputFieldConfirmPassword
import com.mh.impl.models.RegistrationInputFieldEmail
import com.mh.impl.models.RegistrationInputFieldPassword
import com.musicianhelper.core.ui.images.CircleBorderImage
import com.musicianhelper.core.ui.inputfields.DefaultOutlinedField
import com.musicianhelper.registration.impl.NavigateToMain
import com.musicianhelper.registration.impl.PictureSelected
import com.musicianhelper.registration.impl.R
import com.musicianhelper.registration.impl.RegistrationState
import com.musicianhelper.registration.impl.RegistrationState.Default
import com.musicianhelper.registration.impl.RegistrationState.ShowPhotoSource
import com.musicianhelper.registration.impl.RegistrationViewModel
import com.musicianhelper.registration.impl.ShowPickSourceDialogEvent
import com.musicianhelper.registration.impl.SignInEvent
import com.musicianhelper.registration.impl.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun RegistrationScreen(
  navController: NavController,
  viewModel: RegistrationViewModel,
  state: RegistrationState
) {
  val coroutineScope = rememberCoroutineScope()

  var pictureUri by remember { mutableStateOf<Uri?>(null) }
  val launcher = rememberLauncherForActivityResult(
    contract = StartActivityForResult(),
    onResult = {
      pictureUri = it.data?.data
      viewModel.dispatchEvent(PictureSelected)
    })

  SideEffect {
    // Timber.d("RECOMPOSITION")
  }

  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirmedPassword by remember { mutableStateOf("") }

  when (state) {
    is ShowPhotoSource -> {
      val intent =
        Intent(
          Intent.ACTION_OPEN_DOCUMENT,
          Media.EXTERNAL_CONTENT_URI
        ).apply {
          addCategory(Intent.CATEGORY_OPENABLE)
          type = "image/*"
        }
      launcher.launch(intent)
    }

    is Default -> {
      val items = state.itemsList
      if (items.isNotEmpty()) {
        LazyColumn(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          items(items, itemContent = {
            ColumnItemsFactory(
              viewModel = viewModel,
              item = it,

              onEmailChanged = { email = it },
              emailValue = email,
              onPasswordChanged = { password = it },
              passwordValue = password,
              onConfirmPasswordChanged = { confirmedPassword = it },
              confirmPasswordValue = confirmedPassword,
              pictureUri = pictureUri
            )
          })

          item {
            Button(
              enabled = email.isNotEmpty() && password.isNotEmpty() && confirmedPassword.isNotEmpty(),
              onClick = { viewModel.dispatchEvent(SignInEvent(email, password)) }
            ) {
              Text(text = "Sign In")
            }
          }
        }
      }
    }
  }

  LaunchedEffect(coroutineScope) {
    viewModel.collectNavigation().collectLatest { navigation ->
      when (navigation) {
        is NavigateToMain -> {
          // TODO think about abstraction
          navController.navigate("main-screen") {
            popUpTo(navController.currentBackStackEntry?.destination?.route ?: return@navigate) {
              inclusive = true
            }
          }
        }
      }
    }
  }
}

@Composable
fun InputFieldTrailingIcon(
  onClick: () -> Unit,
  icon: ImageVector = Icons.Default.Clear,
  tint: Color = Color.Black
) {
  IconButton(onClick = { onClick() }) {
    Icon(
      imageVector = icon,
      contentDescription = "",
      tint = tint
    )
  }
}

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun ColumnItemsFactory(
  viewModel: RegistrationViewModel,
  item: ViewModel,
  onEmailChanged: (String) -> Unit,
  emailValue: String,
  onPasswordChanged: (String) -> Unit,
  passwordValue: String,
  onConfirmPasswordChanged: (String) -> Unit,
  confirmPasswordValue: String,
  pictureUri: Uri?
) {
  when (item) {
    is RegistrationInputFieldPassword -> {
      DefaultOutlinedField(
        onValueChange = { onPasswordChanged.invoke(it) },
        value = passwordValue,
        label = { Text(text = item.title) },
        icon = {
          Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
          )
        },
      )
    }
    is RegistrationInputFieldConfirmPassword -> {
      DefaultOutlinedField(
        onValueChange = { onConfirmPasswordChanged.invoke(it) },
        value = confirmPasswordValue,
        label = { Text(text = item.title) },
        icon = {
          Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
          )
        }
      )
    }
    is RegistrationInputFieldEmail -> {
      DefaultOutlinedField(
        onValueChange = { onEmailChanged.invoke(it) },
        value = emailValue,
        label = { Text(text = item.title) },
        icon = {
          Icon(
            imageVector = Icons.Default.Email,
            contentDescription = null,
          )
        }
      )
    }
    is RegistrationImage -> {
      CircleBorderImage(
        isClickable = true,
        pictureUri = pictureUri,
        size = 100.dp,
        verticalPadding = 24.dp,
        borderWidth = 2.dp,
        defaultDrawable = R.drawable.ic_add_photo,
        onClick = { viewModel.dispatchEvent(ShowPickSourceDialogEvent) }
      )
    }
  }
}
