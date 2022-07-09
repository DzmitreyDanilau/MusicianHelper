package com.mh.impl.mappers

import com.mh.impl.RegistrationFieldViewModel
import com.musicianhelper.ViewModel
import com.musicianhelper.common.android.ResourceProvider
import javax.inject.Inject

class RegistrationFieldsMapper @Inject constructor(
  private val resourceProvider: ResourceProvider
) {

  fun map(): List<ViewModel> {
    val items = mutableListOf<RegistrationFieldViewModel>()

    items.add(
      RegistrationFieldViewModel("0", "Email", "")
    )
    items.add(
      RegistrationFieldViewModel("1", "Name", "")
    )
    items.add(
      RegistrationFieldViewModel("2", "Second Name", "")
    )
    items.add(
      RegistrationFieldViewModel("3", "Address", "")
    )
    items.add(
      RegistrationFieldViewModel("4", "Zip-code", "")
    )
    items.add(
      RegistrationFieldViewModel("5", "Phone number", "")
    )

    return items
  }
}