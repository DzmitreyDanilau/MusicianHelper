package com.mh.impl

import com.musicianhelper.ViewModel

data class RegistrationFieldViewModel(
  override val id: String,
  val title: String,
  val value: String
) : ViewModel(id = id)