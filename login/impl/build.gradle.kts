plugins {
  id("musicianhelper.android.library")
  id("musicianhelper.android.feature")
  id("musicianhelper.android.library.compose")
}

dependencies {
  api(project(":login:api"))
  implementation(project(":data:api"))
}
