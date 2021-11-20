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

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.compose
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {

  implementation(Dependencies.Compose.composeUI)
  implementation(Dependencies.Compose.composeActivity)
  implementation(Dependencies.Compose.composeCompiler)
  implementation(Dependencies.Compose.composeLifeCycleViewModel)
  implementation(Dependencies.Compose.composeNavigation)

  implementation(Dependencies.Dagger.dagger)
  kapt(Dependencies.Dagger.kapt)

  implementation(Dependencies.stdLibjdk8)
  implementation(Dependencies.Kotlin.coroutinesCore)
}