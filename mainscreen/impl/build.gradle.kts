plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

repositories {
  google()
  mavenCentral()
  maven(url = "https://jitpack.io")
  maven(url = "https://maven.google.com")
}

android {
  compileSdk = ConfigData.compileSdkVersion

  defaultConfig {
    minSdk = ConfigData.minSdkVersion
    targetSdk = ConfigData.targetSdkVersion
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.compose
  }
}

dependencies {

  implementation(project(":common"))
  implementation(project(":platform:ui"))
  implementation(project(":data:api"))
  api(project(":mainscreen:api"))

  implementation(Dependencies.Dagger.dagger)
  kapt(Dependencies.Dagger.kapt)

  implementation(Dependencies.Compose.composeUI)
  implementation(Dependencies.Compose.composeActivity)
  implementation(Dependencies.Compose.composeUiTooling)
  implementation(Dependencies.Compose.composeMaterial)
  implementation(Dependencies.Compose.composeCompiler)
  implementation(Dependencies.Compose.composeLifeCycleViewModel)
  implementation(Dependencies.Compose.composeAnimation)
  implementation(Dependencies.Compose.composeNavigation)
  implementation(Dependencies.Compose.composeRuntime)
  implementation(Dependencies.Compose.composeFoundation)
  implementation(Dependencies.Compose.composeGoogleMaps)

  implementation(Dependencies.PlayServices.googleMaps)
}
