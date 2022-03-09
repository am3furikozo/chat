/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

val ktorVersion: String by project
val logbackVersion: String by project
val composeVersion: String by project

plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
  id("org.jetbrains.kotlin.plugin.serialization")
}

android {
  compileSdk = 31

  defaultConfig {
    applicationId = "me.amefurikozo.chat_app.client"
    minSdk = 24
    targetSdk = 31
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility(JavaVersion.VERSION_1_8)
    targetCompatibility(JavaVersion.VERSION_1_8)
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  buildFeatures {
    compose = true
    viewBinding = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = composeVersion
  }
}

kapt {
  correctErrorTypes = true
}

hilt {
  enableAggregatingTask = true
}

dependencies {
  // Compose
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
  implementation("androidx.navigation:navigation-compose:2.4.1")
  implementation("androidx.activity:activity-compose:1.4.0")
  implementation("androidx.compose.ui:ui:$composeVersion")
  implementation("androidx.compose.material:material:$composeVersion")
  implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
  implementation("androidx.core:core-ktx:1.7.0")

  // Theming
  implementation("com.google.android.material:material:1.5.0")

  // Networking
  implementation("io.ktor:ktor-client-core:$ktorVersion")
  implementation("io.ktor:ktor-client-cio:$ktorVersion")
  implementation("io.ktor:ktor-client-serialization:$ktorVersion")
  implementation("io.ktor:ktor-client-websockets:$ktorVersion")

  // Coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

  // Coroutine Lifecycle Scopes
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

  // IOC
  implementation("com.google.dagger:hilt-android:2.41")
  implementation("androidx.constraintlayout:constraintlayout:2.1.3")
  implementation("androidx.navigation:navigation-fragment-ktx:2.4.1")
  implementation("androidx.navigation:navigation-ui-ktx:2.4.1")
  kapt("com.google.dagger:hilt-android-compiler:2.41")
  kapt("androidx.hilt:hilt-compiler:1.0.0")
  implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

  // Logging
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
  implementation("io.ktor:ktor-client-logging:$ktorVersion")

  // Compatibility
  implementation("androidx.appcompat:appcompat:1.4.1")

  // Debugging
  debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

  // Testing
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
  androidTestImplementation("com.google.dagger:hilt-android-testing:2.41")
  kaptAndroidTest("com.google.dagger:hilt-compiler:2.41")
  testImplementation("com.google.dagger:hilt-android-testing:2.41")
  kaptTest("com.google.dagger:hilt-compiler:2.41")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
}
