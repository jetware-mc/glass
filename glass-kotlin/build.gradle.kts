

plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "com.thedevjade.glass-kotlin"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("io.github.kawamuray.wasmtime:wasmtime-java:0.19.0")
    implementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
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
    mainClass.set("MainKt")
}