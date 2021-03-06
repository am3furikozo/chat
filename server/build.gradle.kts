/*
 * *********************************************************************************************
 * Copyright (c) 2022.
 * This file is part of Chat project which is released under GNU General Public License v3.0.
 * See LICENSE file or go to https://www.gnu.org/licenses/gpl-3.0.en.html for full license details.
 * *********************************************************************************************
 */

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val kmongoVersion: String by project
val koinVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.0"
}

group = "me.amefurikozo.chat_app.server"
version = "0.0.1"
application {
    mainClass.set("me.amefurikozo.chat_app.server.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    // Messaging
    implementation("io.ktor:ktor-websockets:$ktorVersion")

    // Authentication & Sessions
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")

    // Serialization
    implementation("io.ktor:ktor-serialization:$ktorVersion")

    // Data
    implementation("org.litote.kmongo:kmongo:$kmongoVersion")
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongoVersion")

    // IOC
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // Testing
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}