package com.mh.ui.image

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CircleBorderImage(
  pictureUri: Uri?,
  onClick: () -> Unit,
  size: Dp,
  @DrawableRes defaultDrawable: Int,
  borderColor: Color = Color.Gray,
  isClickable: Boolean = true,
  verticalPadding: Dp = 0.dp,
  horizontalPadding: Dp = 0.dp,
  borderWidth: Dp = 0.dp
) {
  Column(modifier = Modifier.padding(horizontal = 24.dp)) {
    AsyncImage(
      model = pictureUri ?: defaultDrawable,
      contentScale = pictureUri?.let { ContentScale.Crop } ?: ContentScale.Fit,
      contentDescription = null,
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .size(size)
        .clip(CircleShape)
        .border(borderWidth, borderColor, CircleShape)
        .padding(
          vertical = getPadding(pictureUri, verticalPadding),
          horizontal = getPadding(pictureUri, horizontalPadding)
        )
        .clickable(
          enabled = isClickable,
          onClick = { onClick() }
        )
    )
  }
}

private fun getPadding(
  pictureUri: Uri?,
  padding: Dp
): Dp {
  return if (pictureUri != null) {
    0.dp
  } else {
    padding
  }
}
