plugins {
  id("musicianhelper.android.library")
  id("musicianhelper.android.library.compose")
  kotlin("kapt")
}

dependencies {
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.lifecycle.viewModelCompose)
  implementation(libs.dagger)
  kapt(libs.dagger.compiler)
}