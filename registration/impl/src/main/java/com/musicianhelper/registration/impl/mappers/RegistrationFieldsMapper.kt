package com.musicianhelper.registration.impl.mappers

import com.musicianhelper.registration.impl.ViewModel
import com.mh.impl.models.RegistrationImage
import com.mh.impl.models.RegistrationInputFieldConfirmPassword
import com.mh.impl.models.RegistrationInputFieldEmail
import com.mh.impl.models.RegistrationInputFieldPassword
import com.musicianhelper.core.common.ResourceProvider
import javax.inject.Inject

class RegistrationFieldsMapper @Inject constructor(
  private val resourceProvider: ResourceProvider
) {

  fun map(): List<ViewModel> {
    val items = mutableListOf<ViewModel>()
    items.add(
      RegistrationImage(id = "0", imageUrl = null)
    )
    items.add(
      RegistrationInputFieldEmail("1", "", "Email")
    )
    items.add(
      RegistrationInputFieldPassword("2", "", "Password")
    )
    items.add(
      RegistrationInputFieldConfirmPassword("3", "", "Confirm Password")
    )

    return items
  }
}