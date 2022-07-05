package com.mh.ui.image

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CircleBorderImage(
  isClickable: Boolean = true,
  pictureUri: Uri?,
  size: Dp,
  verticalPadding: Dp = 0.dp,
  horizontalPadding: Dp = 0.dp,
  borderWidth: Dp,
  borderColor: Color,
  @DrawableRes
  defaultDrawable: Int,
  onClick: () -> Unit
) {
  Image(
    painter = rememberAsyncImagePainter(model = pictureUri ?: defaultDrawable),
    contentDescription = "Circle_Image",
    contentScale = pictureUri?.let { ContentScale.Crop } ?: ContentScale.Fit,
    modifier = Modifier
      .padding(vertical = verticalPadding, horizontal = horizontalPadding)
      .size(size)
      .clip(CircleShape)
      .border(borderWidth, borderColor, CircleShape)
      .clickable(
        enabled = isClickable,
        onClick = { onClick() }
      ),
  )
}
