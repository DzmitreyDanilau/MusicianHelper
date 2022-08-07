package com.musicianhelper.core.ui.text

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun AnnotatedClickableText(
  tag: String,
  tagColor: Color,
  text: String,
  textColor: Color,
  onClick: () -> Unit
) {
  val annotatedText = buildAnnotatedString {
    withStyle(
      style = SpanStyle(color = textColor)
    ) {
      append(text)
    }
    pushStringAnnotation(
      tag = tag, // provide tag which will then be provided when we click the text
      annotation = tag
    )
    withStyle(
      style = SpanStyle(color = tagColor)
    ) {
      append(tag)
    }
    pop()
  }

  ClickableText(
    modifier = Modifier.padding(vertical = 24.dp),
    text = annotatedText,
    onClick = { offset ->
      annotatedText.getStringAnnotations(
        tag = tag,
        start = offset,
        end = offset
      )[0].apply { onClick() }
    }
  )
}
