@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.themovieguide.org"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.themovieguide.org"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".dev"
            getProps(rootProject.file("debug.properties")).forEach { prop ->
                buildConfigField("String", prop.key.toString(), prop.value.toString())
            }
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":data"))
    // implementation(project(":domain"))
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.window:window-core:1.1.0-beta02")
    implementation("com.google.ar.sceneform:sceneform-base:1.17.1")
    implementation(files("D:/YouTubeAndroidPlayerApi.jar"))
    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    val naVersion = "2.5.3"

    implementation("androidx.navigation:navigation-compose:$naVersion")
    /**
     * Timber**/
    implementation("com.jakewharton.timber:timber:5.0.1")

    /*** room ***/
    implementation("androidx.room:room-runtime:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")

    /*** room kotlin extension and coroutines support ***/
    implementation("androidx.room:room-ktx:2.5.1")

    /** retrofit **/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    /** chucker interceptor **/
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    implementation("androidx.webkit:webkit:1.6.1")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")
    /** httpLoggin **/
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("androidx.fragment:fragment-ktx:1.5.6")
    /** Hilt Dagger **/
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")

    /*** ViewModel ***/
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    /** KOTLIN **/
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    /** GSON **/
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    /** compose runtime **/
    implementation("androidx.compose.runtime:runtime:1.4.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.4.2")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.4.2")
    implementation("com.google.android.exoplayer:exoplayer:2.18.6")
    implementation("com.github.HaarigerHarald:android-youtubeExtractor:master-SNAPSHOT")

    val dynamicanimation_version = "1.0.0"
    implementation("androidx.dynamicanimation:dynamicanimation:$dynamicanimation_version")
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")
    /** compose ui **/

    implementation("androidx.compose.ui:ui-util")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-graphics:1.4.2")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation:1.4.2")
    implementation("androidx.compose.material:material:1.4.2")
    implementation("androidx.compose.material:material-ripple:1.4.2")
    implementation("androidx.compose.material:material-icons-extended:1.4.2")
    implementation("com.google.accompanist:accompanist-coil:0.15.0")
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("com.google.accompanist:accompanist-pager:0.30.1")
    implementation("androidx.compose.material3:material3:1.1.0-rc01")
    implementation("org.burnoutcrew.composereorderable:reorderable:0.9.6")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.0-alpha")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.ui:ui:1.4.2")
    implementation("androidx.compose.ui:ui-tooling:1.4.2")
}

fun getProps(file: File): java.util.Properties {
    val props = Properties()
    props.load(file.inputStream())
    return props
}
