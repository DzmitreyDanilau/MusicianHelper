plugins {
  id("musicianhelper.android.library")
  id("musicianhelper.android.library.compose")
}

dependencies {
  implementation(project(":core-designsystem"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.coil.kt)
  implementation(libs.coil.kt.compose)
  implementation(libs.kotlinx.datetime)

  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
  api(libs.androidx.compose.material.iconsExtended)
  api(libs.androidx.compose.material3)
  debugApi(libs.androidx.compose.ui.tooling)
  api(libs.androidx.compose.ui.tooling.preview)
  api(libs.androidx.compose.ui.util)
  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.runtime.livedata)
  api(libs.androidx.metrics)
  api(libs.androidx.tracing.ktx)
}