plugins {
  id("com.android.library")
}

repositories {
  google()
  mavenCentral()
  maven(url = "https://jitpack.io")
  maven(url = "https://maven.google.com")
}

android {
  compileSdk =  ConfigData.compileSdkVersion

  defaultConfig {
    minSdk  = ConfigData.minSdkVersion
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

  implementation(Dependencies.Firebase.firebaseAuth)
  implementation(Dependencies.Firebase.firebaseAuthKtx)
  implementation(Dependencies.Firebase.firebaseAuthKtx)
  implementation(Dependencies.stdLibjdk8)
}
