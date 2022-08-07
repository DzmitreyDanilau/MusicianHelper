plugins {
  id("musicianhelper.android.library")
  id("musicianhelper.android.feature")
  id("musicianhelper.android.library.compose")
}

dependencies {
  api(project(":main-screen:api"))
  implementation(project(":data:api"))
}
