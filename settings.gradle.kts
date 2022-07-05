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
                useModule("com.android.tools.build:gradle:7.2.1")
            }
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.6.10")
            }
            if (requested.id.id.startsWith("com.google.gms")) {
                useModule("com.google.gms:google-services:4.3.8")
            }
        }
    }
}

rootProject.name = "MusicianHelper"

include(":app")
include(":common")

include(":login:impl", ":login:api")
include(":registration:api", ":registration:impl")

include(":data:firebase")
include(":data:api")

include(":platform:ui")
include(":uikit")
