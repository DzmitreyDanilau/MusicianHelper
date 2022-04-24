plugins {
  id("com.android.application")
  id("com.google.gms.google-services")
  kotlin("android")
  kotlin("kapt") version "1.5.21"
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
    applicationId = ConfigData.applicationName
    minSdk = ConfigData.minSdkVersion
    targetSdk = ConfigData.targetSdkVersion
    buildToolsVersion = ConfigData.buildToolsVersion
    versionCode = Versions.versionCode
    versionName = Versions.versionName

    vectorDrawables {
      useSupportLibrary = true
    }
    signingConfig = signingConfigs.getByName("debug")
  }

  buildTypes {
    debug {

    }

    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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

  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}



dependencies {

  implementation(project(":common"))
  implementation(project(":login:impl"))
  implementation(project(":data:firebase"))

  implementation(Dependencies.Core.activityKtx)
  implementation(Dependencies.Core.coreKtx)

  implementation(Dependencies.Design.materialDesign)

  implementation(Dependencies.Compose.composeActivity)
  implementation(Dependencies.Compose.composeAnimation)
  implementation(Dependencies.Compose.composeCompiler)
  implementation(Dependencies.Compose.composeLifeCycleViewModel)
  implementation(Dependencies.Compose.composeMaterial)
  implementation(Dependencies.Compose.composeUiTooling)
  implementation(Dependencies.Compose.composeNavigation)

  implementation(Dependencies.LifeCycle.lifecycleCommonJava8)
  implementation(Dependencies.LifeCycle.lifecycleRuntimeKtx)
  implementation(Dependencies.LifeCycle.lifecycleViewmodelKtx)

  implementation(Dependencies.Dagger.dagger)
  kapt(Dependencies.Dagger.kapt)

  testImplementation("junit:junit:4.+")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.0.4")
  debugImplementation("androidx.compose.ui:ui-tooling:1.0.4")
}