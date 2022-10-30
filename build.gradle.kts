import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("com.adarshr.test-logger") version "3.1.0"
}

group = "org.jub.solovev.lockfreestack"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:atomicfu:0.18.5")
    testApi(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:lincheck-jvm:2.15")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
