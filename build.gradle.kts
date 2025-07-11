import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.kotlin.dsl.implementation

val projectName = "TypingGame"
val projectVersion = "1.0.0"
val projectGroup = "jp.ac.tbc_u.typing_game"
val projectArtifact = "Main"
val projectMainClass = "$projectGroup.$projectArtifact"

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin"))
    }
}

plugins {
    java
    kotlin("jvm") version "2.1.21"
    application
    id("com.gradleup.shadow") version "8.3.0"
}

group = projectGroup
version = projectVersion
application {
    mainClass.set(projectMainClass)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:2.1.21"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.9.0")

    implementation("com.formdev", "flatlaf", "3.6")

    implementation(fileTree("libs"))
}

kotlin {
    jvmToolchain(11)
}

val jar: Jar by tasks
jar.apply {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    manifest {
        attributes(
            mapOf(
                "Main-Class" to projectMainClass,
                "Class-Path" to "."
            )
        )
    }

    isZip64 = true
    from(
        configurations.runtimeClasspath.get().filter {
            !it.extension.equals("pom", true)
        }.map {
            if (it.isDirectory) it else zipTree(it)
        }
    )

    archiveBaseName.set(projectName)
}

val shadowJar: ShadowJar by tasks
shadowJar.apply {
    isZip64 = true

    archiveBaseName.set(projectName)
}