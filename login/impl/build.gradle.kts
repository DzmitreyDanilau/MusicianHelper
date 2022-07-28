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
    api(project(":login:api"))

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
}
