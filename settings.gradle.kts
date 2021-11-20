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
      if (requested.id.id.startsWith("com.google.android")) {
        useModule("com.google.android.gms:play-services-location:18.0.0")
      }
    }
  }
}

rootProject.name = "MusicianHelper"
include(":app")
include(":common")

include(":login:impl", ":login:api")
include(":data:firebase")
include(":data:api")
