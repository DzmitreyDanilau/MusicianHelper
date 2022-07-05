package com.mh.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun <T> ColumnTest(
  list: List<T>,
  factory: @Composable (item: T) -> Unit
) {
  LazyColumn {
      items(list, contentType = {
      }) {
        factory(it)
      }
  }
}

