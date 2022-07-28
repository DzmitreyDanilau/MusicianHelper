import Versions.javaVersion

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

  buildTypes {

    debug {

    }

    release {

    }
  }

  compileOptions {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
  }

  kotlinOptions {
    jvmTarget = "1.8"
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

  implementation(Dependencies.Compose.composeUI)
  implementation(Dependencies.Compose.composeActivity)
  implementation(Dependencies.Compose.composeMaterial)
  implementation(Dependencies.Compose.composeCompiler)
  implementation(Dependencies.Compose.composeLifeCycleViewModel)
  implementation(Dependencies.Compose.composeAnimation)
  implementation(Dependencies.Compose.composeNavigation)
  implementation(Dependencies.Compose.composeRuntime)
  implementation(Dependencies.Compose.composeFoundation)

  debugImplementation(Dependencies.Compose.composeUiTooling)
  implementation(Dependencies.Compose.composeToolingPreview)

  implementation(Dependencies.ImagesLoader.coil)
}