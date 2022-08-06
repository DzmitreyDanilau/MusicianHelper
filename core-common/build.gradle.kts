plugins {
  id("musicianhelper.android.library")
  kotlin("kapt")
}

dependencies {

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)

}