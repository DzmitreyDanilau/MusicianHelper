plugins {
  id("com.android.library")
  kotlin("android")
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

  implementation(Dependencies.Firebase.firebaseAuth)
  implementation(Dependencies.Firebase.firebaseAuthKtx)

  implementation(Dependencies.Kotlin.coroutinesCore)
  implementation(Dependencies.stdLibjdk8)

  implementation(Dependencies.Compose.composeUI)
  implementation(Dependencies.Compose.composeActivity)
  implementation(Dependencies.Compose.composeUiTooling)
  implementation(Dependencies.Compose.composeMaterial)
  implementation(Dependencies.Compose.composeCompiler)
  implementation(Dependencies.Compose.composeLifeCycleViewModel)
  implementation(Dependencies.Compose.composeAnimation)
}
