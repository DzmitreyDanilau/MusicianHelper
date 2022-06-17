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

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":common"))
}