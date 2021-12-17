object ApplicationId {
    const val id = "com.example.tipjar"
}

object Modules {
    const val app = ":app"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val appcompat = "1.4.0-alpha03"
    const val camerax = "1.1.0-alpha07"
    const val cameraView = "1.0.0-alpha27"
    const val chucker = "3.5.0"
    const val compileSdk = 30
    const val constraintLayout = "2.0.4"
    const val coreKtx = "1.6.0"
    const val coreTest = "1.4.0"
    const val coroutines = "1.5.1"
    const val design = "1.5.0-alpha01"
    const val espresso = "3.4.0"
    const val flipper = "0.98.0"
    const val glide = "4.12.0"
    const val gradle = "4.2.1"
    const val junit = "1.1.3"
    const val koin = "3.1.2"
    const val kotlin = "1.5.21"
    const val lifecycle = "2.4.0-alpha02"
    const val lifecycleTesting = "2.1.0"
    const val materialComponents = "1.4.0"
    const val minSdk = 21
    const val mockk = "1.12.0"
    const val moshi = "1.12.0"
    const val navVersion = "2.3.5"
    const val okhttp = "4.9.1"
    const val retrofit = "2.9.0"
    const val roboelectric = "4.6.1"
    const val room = "2.2.6"
    const val rules = "1.4.0"
    const val runner = "1.4.0"
    const val soLoader = "0.10.1"
    const val targetSdk = 30
    const val timber = "4.7.1"
    const val truth = "1.4.0"
    const val windowManager = "1.0.0-alpha09"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleCommonJava8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val materialComponents =
        "com.google.android.material:material:${Versions.materialComponents}"
    const val navigationSafeArgsPlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}"

    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    // DB
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val windowManager = "androidx.window:window:${Versions.windowManager}"
}

object DI {
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
}

object Google {
    const val material = "com.google.android.material:material:${Versions.design}"
}

object Kotlin {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object Network {
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
    const val kotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiAdapter = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
}

object Testing {
    const val junit = "androidx.test.ext:junit:${Versions.junit}"
    const val truth = "androidx.test.ext:truth:${Versions.truth}"
    const val mockK = "io.mockk:mockk:${Versions.mockk}"
    const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycleTesting}"
    const val core = "androidx.test:core:${Versions.coreTest}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val roboelectric = "org.robolectric:robolectric:${Versions.roboelectric}"
    const val rules = "androidx.test:rules:${Versions.rules}"
    const val runner = "androidx.test:rules:${Versions.runner}"
}

object UI {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Utils {
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val flipper = "com.facebook.flipper:flipper:${Versions.flipper}"
    const val flipperFrescoPlugin = "com.facebook.flipper:flipper-fresco-plugin:${Versions.flipper}"
    const val flipperNetworkPlugin =
        "com.facebook.flipper:flipper-network-plugin:${Versions.flipper}"
    const val soLoader = "com.facebook.soloader:soloader:${Versions.soLoader}"
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.3.1"
    const val threeTenBp = "org.threeten:threetenbp:1.5.1"
}
