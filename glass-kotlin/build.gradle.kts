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
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("io.github.kawamuray.wasmtime:wasmtime-java:0.19.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("com.github.kittinunf.fuel:fuel:3.0.0-alpha1")
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
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
    mainClass.set("com.thedevjade.glass.MainKt")
}