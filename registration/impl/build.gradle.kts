plugins {
  id("musicianhelper.android.library")
  id("musicianhelper.android.library.compose")
  id("musicianhelper.android.feature")
}

dependencies {
  api(project(":registration:api"))
  implementation(project(":data:api"))

  implementation(libs.androidx.lifecycle.viewModelCompose)
}