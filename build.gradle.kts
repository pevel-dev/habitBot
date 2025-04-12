plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://jitpack.io")
    mavenCentral()
}

val ktormVersion: String by project
val kotlinTelegramBotVersion: String by project
val kodeinVersion: String by project

dependencies {
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:${kotlinTelegramBotVersion}")
    implementation("org.ktorm:ktorm-core:${ktormVersion}")
    implementation("org.kodein.di:kodein-di:${kodeinVersion}")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

dependencies {
}