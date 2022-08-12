plugins {
  id("musicianhelper.android.library")
  id("musicianhelper.android.library.compose")
}

dependencies {
  implementation(project(":core-common"))
  implementation(libs.kotlin.coroutines.core)
  implementation(libs.androidx.compose.runtime)
}