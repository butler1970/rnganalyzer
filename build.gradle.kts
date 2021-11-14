import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    application
}

group = "com.tiwalasautak"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("com.jakewharton.picnic:picnic:0.5.0")

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("com.tiwalasautak.rng.Application")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

// build.gradle.kts (Kotlin syntax)
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}