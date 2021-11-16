import org.gradle.api.JavaVersion

object Versions {

  const val versionCode = 1
  const val versionName = "1.0"
  const val compose = "1.0.1"

  object Navigation {
    const val navVersion = "2.2.0-rc03"
  }

  object Hilt {
    const val version = "2.38.1"
    const val kapt = "2.38.1"
    const val navigation = "1.0.0-alpha03"
    const val compiler = "2.38.1"
  }

  val javaVersion = JavaVersion.VERSION_11
}


