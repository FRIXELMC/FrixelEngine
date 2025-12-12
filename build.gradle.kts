import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.2.21"
    id("com.gradleup.shadow") version "9.2.2"
    id("java")
    id("com.palantir.git-version") version "4.2.0"
    `java-library`
}

val git : String = versionBanner()
val builder : String = builder()
ext["git_version"] = git
ext["builder"] = builder

val isDev: Boolean = true

group = "com.github.frixel.frixelengine"
version = if (isDev) {
    "${rootProject.properties["project_version"]!!}-$git-dev"
} else {
    "${rootProject.properties["project_version"]!!}-$git"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.nexomc.com/releases")
    maven("https://maven.devs.beer/")
    maven("https://repo.momirealms.net/releases/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    compileOnly("com.nexomc:nexo:1.12.0-dev")
    compileOnly("dev.lone:api-itemsadder:4.0.10")
    compileOnly("net.momirealms:craft-engine-core:0.0.66")
    compileOnly("net.momirealms:craft-engine-bukkit:0.0.66")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks {
    register<ShadowJar>("shadowJarPlugin") {
        archiveFileName.set("FrixelEngine-${version}.jar")

        from(sourceSets.main.get().output)
        configurations = listOf(project.configurations.runtimeClasspath.get())

        exclude("com/zaxxer/**")
        exclude("kotlin/**", "kotlinx/**")
        exclude("org/intellij/**")
        exclude("org/jetbrains/**")
        exclude("org/slf4j/**")
    }

    build {
        dependsOn("shadowJarPlugin")
    }

    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}

fun versionBanner(): String = project.providers.exec {
    commandLine("git", "rev-parse", "--short=8", "HEAD")
}.standardOutput.asText.map { it.trim() }.getOrElse("Unknown")

fun builder(): String = project.providers.exec {
    commandLine("git", "config", "user.name")
}.standardOutput.asText.map { it.trim() }.getOrElse("Unknown")