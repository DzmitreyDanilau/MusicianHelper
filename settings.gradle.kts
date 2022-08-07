pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    // Register the AndroidX snapshot repository first so snapshots don't attempt (and fail)
    // to download from the non-snapshot repositories
    maven(url = "https://androidx.dev/snapshots/builds/8455591/artifacts/repository") {
      content {
        // The AndroidX snapshot repository will only have androidx artifacts, don't
        // bother trying to find other ones
        includeGroupByRegex("androidx\\..*")
      }
    }
    google()
    mavenCentral()
  }
}

rootProject.name = "MusicianHelper"

include(":app")
include(":core-common")
include(":core-navigation")
include(":core-designsystem")
include(":core-ui")
include(":login:api", ":login:impl")
include(":data:api")
include(":data:firebase")
include(":registration:api",":registration:impl")
include(":main-screen:api",":main-screen:impl")
