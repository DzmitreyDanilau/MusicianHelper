plugins {
  id("musicianhelper.android.library")
  kotlin("kapt")
}

dependencies {
  implementation(project(":core-common"))
  api(project(":data:api"))


  implementation(libs.firebase.core)
  implementation(libs.firebase.auth)
  implementation(libs.firebase.ktx.firestore)

  implementation(libs.play.services.coroutines)
  implementation(libs.google.maps)

  implementation(libs.kotlin.coroutines.core)

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)
}