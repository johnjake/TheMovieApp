@file:Suppress("UnstableApiUsage")

import java.util.Properties

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.themovieguide.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        getByName("debug") {
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
        buildConfig = true
    }
}

dependencies {

    implementation(project(":domain"))

    /** chucker interceptor **/
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    /** paging **/
    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    /*** room ***/
    implementation("androidx.room:room-runtime:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")

    /*** room kotlin extension and coroutines support ***/
    implementation("androidx.room:room-ktx:2.5.1")

    /** retrofit **/
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    /** Timber **/
    implementation("com.jakewharton.timber:timber:5.0.1")

    /** httpLogging **/
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    /** GSON **/
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    /** kotlin serialization **/
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    /** youtube url **/
    implementation("com.github.HaarigerHarald:android-youtubeExtractor:master-SNAPSHOT")

    /** Hilt Dagger **/
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

fun getProps(file: File): java.util.Properties {
    val props = Properties()
    props.load(file.inputStream())
    return props
}
