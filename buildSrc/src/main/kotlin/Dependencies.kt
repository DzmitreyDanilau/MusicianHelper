object Dependencies {

  object Common {
    private const val appCompatVersion = "1.3.0"
    val appCompat by lazy { "androidx.appcompat:appcompat:$jUnitVersion" }

    private const val jUnitVersion = "4.12"
    val junit by lazy { "junit:junit:$appCompatVersion" }

    private const val timberVersion = "4.7.1"
    val timber by lazy { "com.jakewharton.timber:timber:$timberVersion" }

    private const val findBugsVersion = "3.0.2"
    val findBugs by lazy { "com.google.code.findbugs:jsr305:$findBugsVersion" }
  }

  object Design {
    private const val version = "1.3.0"
    val materialDesign by lazy { "com.google.android.material:material:$version" }
  }

  object LifeCycle {
    private const val version = "2.4.0"

    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:$version" }
    val lifecycleCommonJava8 by lazy { "androidx.lifecycle:lifecycle-common-java8:$version" }
    val lifecycleViewmodelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$version" }
  }

  object Core {
    private const val coreKtxVersion = "1.7.0"
    private const val activityKtxVersion = "1.4.0"
    val coreKtx by lazy { "androidx.core:core-ktx:$coreKtxVersion" }
    val activityKtx by lazy { "androidx.activity:activity-ktx:$activityKtxVersion" }
  }

  object Kotlin {
    private const val coroutinesCoreVersion = "1.5.2"
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion" }
  }

  object Firebase {
    private const val version = "21.0.1"
    private const val coreVersion = "20.0.0"

    val firebaseCore by lazy {"com.google.firebase:firebase-core:$coreVersion"}
    val firebaseAuth by lazy { "com.google.firebase:firebase-auth:$version" }
    val firebaseAuthKtx by lazy { "com.google.firebase:firebase-auth-ktx:$version" }
  }

  object Compose {
      private const val activityVersion = "1.4.0"
    private const val materialVersion = "1.1.1"
    private const val animationVersion = "1.1.1"
    private const val uiToolingVersion = "1.1.1"
    private const val lifeCycleViewModelVersion = "2.4.0"
    private const val compilerVersion = "1.1.0-beta03"
    private const val uiVersion = "1.1.1"
    private const val navigationVersion = "2.4.0-beta02"
    val composeUI by lazy { "androidx.compose.ui:ui:$uiVersion" }
    val composeActivity by lazy { "androidx.activity:activity-compose:$activityVersion" }
    val composeMaterial by lazy { "androidx.compose.material:material:$materialVersion" }
    val composeAnimation by lazy { "androidx.compose.animation:animation:$animationVersion" }
    val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling:$uiToolingVersion" }
    val composeLifeCycleViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:$lifeCycleViewModelVersion" }
    val composeCompiler by lazy { "androidx.compose.compiler:compiler:$compilerVersion" }
    val composeNavigation by lazy { "androidx.navigation:navigation-compose:$navigationVersion"}
  }

  object Dagger {
    private const val version = "2.40.1"

    val dagger by lazy { "com.google.dagger:dagger:$version" }
    val kapt by lazy { "com.google.dagger:dagger-compiler:$version" }
  }

  object PlayServices {
    const val gmsVersion = "4.3.3"
    const val coroutinesVersion = "1.1.1"

    val playServicesCoroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion" }
  }

  val stdLibjdk8 by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21" }
  val stdLibjdk7 by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.21" }
}