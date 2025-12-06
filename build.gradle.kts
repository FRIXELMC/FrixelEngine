import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.2.21"
    id("com.gradleup.shadow") version "9.2.2"
    id("java")
    `java-library`
}

group = "com.github.frixel.frixelengine"
version = "1.0-SNAPSHOT"

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
    compileOnly("net.momirealms:craft-engine-core:0.0.65")
    compileOnly("net.momirealms:craft-engine-bukkit:0.0.65")

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
        archiveFileName.set("FrixelEngine-${project.version}.jar")

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