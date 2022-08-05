
plugins {
    `kotlin-dsl`
}

group = "com.musicianhelper.com.build.logic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "musicianhelper.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
}
