plugins {
  id("musicianhelper.android.application")
  id("musicianhelper.android.application.compose")
  kotlin("kapt")
  id(
    "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
  )
}

android {

  defaultConfig {
    applicationId = "com.musicianhelper"
    versionCode = 1
    versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

    vectorDrawables {
      useSupportLibrary = true
    }
  }
  buildTypes {
    val debug by getting {
      applicationIdSuffix = ".debug"
      signingConfig = signingConfigs.getByName("debug")
    }
    val release by getting {
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }
  packagingOptions {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
  }
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }

  android.sourceSets.all {
    kotlin.srcDir("src/main/kotlin")
  }
}

dependencies {

  implementation(project(":core-common"))
  implementation(project(":core-navigation"))
  implementation(project(":core-designsystem"))
  implementation(project(":core-ui"))
  implementation(project(":login:impl"))
  implementation(project(":registration:impl"))
  implementation(project(":data:firebase"))
  implementation(project(":main-screen:impl"))

  implementation(libs.dagger)
  kapt(libs.dagger.compiler)
}
