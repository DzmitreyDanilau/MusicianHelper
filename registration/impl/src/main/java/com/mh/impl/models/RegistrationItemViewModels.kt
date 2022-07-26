package com.mh.impl.models

import android.net.Uri
import com.mh.impl.ViewModel

class RegistrationInputFieldEmail(
  override val id: String,
  val value: String,
  val title: String
) : ViewModel

class RegistrationInputFieldPassword(
  override val id: String,
  val value: String,
  val title: String
) : ViewModel

class RegistrationInputFieldConfirmPassword(
  override val id: String,
  val value: String,
  val title: String
) : ViewModel

data class RegistrationImage(
  override val id: String,
  val imageUrl: Uri?
) : ViewModel

// abstract class RegistrationItemViewModel(
//   override val id: String
// ) : ViewModel