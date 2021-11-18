pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id.startsWith("com.android")) {
        useModule("com.android.tools.build:gradle:7.0.3")
      }
      if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
        useVersion("1.5.21")
      }
      if (requested.id.id.startsWith("dagger.hilt.android")) {
        useModule("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
      }
    }
  }
}

rootProject.name = "MusicianHelper"
include(":app")
include(":authentication")
include(":common")
