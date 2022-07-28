package com.mh.ui.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun <Int> ColumnTest(
  list: List<Int>,
  factory: @Composable (item: Int) -> Unit

) {
  LazyColumn {
    items(list, contentType = { it }) {
      factory(it)
    }
  }
}

