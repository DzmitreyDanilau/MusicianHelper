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
}

dependencies {

  implementation(project(":common"))
  api(project(":data:api"))

  implementation(Dependencies.Firebase.firebaseCore)
  implementation(Dependencies.Firebase.firebaseAuthKtx)
  implementation(Dependencies.Firebase.firebaseFirestoreKtx)

  implementation(Dependencies.PlayServices.playServicesCoroutines)

  implementation(Dependencies.Kotlin.coroutinesCore)
  implementation(Dependencies.stdLibjdk8)
  implementation(Dependencies.stdLibjdk7)

  implementation(Dependencies.Dagger.dagger)
  kapt(Dependencies.Dagger.kapt)
}
