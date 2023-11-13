plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.5.5"
    id("xyz.jpenilla.run-paper") version "2.2.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("jvm") version "1.9.20"
    application
}

val projectName = "encoral-template"
group = "com.thedevjade.glass-kotlin"
version = "1.0-SNAPSHOT"
description = "Encoral template for development"

java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")

    implementation("io.github.kawamuray.wasmtime:wasmtime-java:0.19.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("io.ktor:ktor-server-core:2.3.6")
    implementation("io.ktor:ktor-server-netty:2.3.6")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")

    implementation("org.mongodb:mongodb-driver-sync:4.11.1")
    implementation("redis.clients:jedis:5.0.0-alpha2")
    api("com.squareup.wire:wire-runtime:4.9.1")

    paperweight.paperDevBundle("1.20.2-R0.1-SNAPSHOT")

    implementation("net.axay:kspigot:1.20.1")
    implementation("com.comphenix.protocol:ProtocolLib:5.1.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.10")
}

tasks {
    // Configure reobfJar to run when invoking the build task
    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
        val props = mapOf(
            "name" to project.name,
            "version" to project.version,
            "description" to project.description,
            "apiVersion" to "1.20"
        )
        inputs.properties(props)
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    reobfJar {
        outputJar.set(layout.buildDirectory.file("libs/$projectName-${project.version}.jar"))
    }
}

sourceSets {
    main {
        java {
            srcDir("src/main/kotlin")
        }
    }
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.thedevjade.glass.MainKt")
}
