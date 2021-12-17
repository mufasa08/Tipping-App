object ApplicationId {
    const val id = "com.example.tipjar"
}

object Modules {
    const val app = ":app"
    const val cache = ":common:cache"
    const val network = ":common:network"
    const val presentation = ":common:presentation"
    const val tipjar = ":tipjar"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val gradle = "4.2.1"
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val appcompat = "1.4.0-alpha03"
    const val design = "1.5.0-alpha01"
    const val cardview = "1.0.0"
    const val recyclerview = "1.2.1"
    const val constraintLayout = "2.1.0-rc01"
    const val coordinatorLayout = "1.1.0"
    const val kotlin = "1.5.21"
    const val coroutines = "1.5.1"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.1"
    const val lifecycle = "2.4.0-alpha02"
    const val lifecycleTesting = "2.1.0"
    const val leakCanary = "2.0-alpha-2"
    const val koin = "3.1.2"
    const val junit = "1.1.3"
    const val truth = "1.4.0"
    const val mockk = "1.12.0"
    const val room = "2.2.6"
    const val swipeRefresh = "1.0.0"
    const val searchableSpinner = "1.3.1"
    const val timber = "4.7.1"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleCommonJava8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

    const val leakCanaryAndroid =
        "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

    const val searchableSpinner =
        "com.toptoche.searchablespinner:searchablespinnerlibrary:${Versions.searchableSpinner}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // DB
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}

object SupportLibraries {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val coordinatorLayout =
        "androidx.coordinatorlayout:coordinatorlayout:${Versions.coordinatorLayout}"
    const val swipeRefresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
}

object TestLibraries {
    const val junit = "androidx.test.ext:junit-ktx:${Versions.junit}"
    const val truth = "androidx.test.ext:truth:${Versions.truth}"
    const val mockK = "io.mockk:mockk:${Versions.mockk}"
    const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycleTesting}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}
