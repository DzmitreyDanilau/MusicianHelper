import com.mh.convention.com.mh.Flavor
import com.mh.convention.com.mh.FlavorDimension

plugins {
  id("musicianhelper.android.application")
  id("musicianhelper.android.application.compose")
  kotlin("kapt")
  // id("com.google.gms.google-services")
  id(
    "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
  )
}

android {

  defaultConfig {
    applicationId = "com.musicianhelper"
    versionCode = 1
    versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

    // Custom test runner to set up Hilt dependency graph
    vectorDrawables {
      useSupportLibrary = true
    }
  }
  buildTypes {
    val debug by getting {
      applicationIdSuffix = ".debug"
    }
    val release by getting {
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )

      // To publish on the Play store a private signing key is required, but to allow anyone
      // who clones the code to sign and run the release variant, use the debug signing key.
      // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
      signingConfig = signingConfigs.getByName("debug")
    }
  }

  // @see Flavor for more details on the app product flavors.
  flavorDimensions += FlavorDimension.contentType.name
  productFlavors {
    Flavor.values().forEach {
      create(it.name) {
        dimension = it.dimension.name
        if (it.applicationIdSuffix != null) {
          applicationIdSuffix = it.applicationIdSuffix
        }
      }
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
}

dependencies {

 implementation(project(":core-common"))
 implementation(project(":core-navigation"))
 implementation(project(":core-designsystem"))
 implementation(project(":core-ui"))
//  implementation(project(":login:impl"))
//  implementation(project(":registration:impl"))
//  implementation(project(":data:firebase"))
//  implementation(project(":mainscreen:impl"))

//  implementation(Dependencies.Core.activityKtx)
//  implementation(Dependencies.Core.coreKtx)
//
//  implementation(Dependencies.Compose.composeActivity)
//  implementation(Dependencies.Compose.composeAnimation)
//  implementation(Dependencies.Compose.composeCompiler)
//  implementation(Dependencies.Compose.composeLifeCycleViewModel)
//  implementation(Dependencies.Compose.composeMaterial)
//  implementation(Dependencies.Compose.composeUiTooling)
//  implementation(Dependencies.Compose.composeNavigation)
//  implementation(Dependencies.Compose.composeRuntime)
//
//  implementation(Dependencies.LifeCycle.lifecycleCommonJava8)
//  implementation(Dependencies.LifeCycle.lifecycleRuntimeKtx)
//  implementation(Dependencies.LifeCycle.lifecycleViewmodelKtx)
//
  implementation(libs.dagger)
  kapt(libs.dagger.compiler)

//
// androidx.test is forcing JUnit, 4.12. This forces it to use 4.13
//  configurations.configureEach {
//    resolutionStrategy {
//      force(libs.junit4)
//       Temporary workaround for https://issuetracker.google.com/174733673
//      force("org.objenesis:objenesis:2.6")
//    }
//  }
}
