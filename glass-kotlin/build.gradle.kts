import groovy.json.JsonSlurper
import java.net.URL


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
}


fun RepositoryHandler.default() {
    mavenCentral()
}


fun Project.addJarDependency(jarFile: File) {
    dependencies {
        implementation(files(jarFile))
    }
}


fun Project.fetchJarFromRelease(platform: String): File {
    val apiUrl = "https://api.github.com/repos/wasmerio/wasmer-java/releases/latest"
    val latestRelease = URL(apiUrl).readText()
    val jsonSlurper = JsonSlurper()
    val response = jsonSlurper.parseText(latestRelease)
    val assetList = (response as Map<String, Any>)["assets"] as List<Map<String, Any>>
    val asset = assetList.find { it["name"].toString().contains(platform) }
    val downloadUrl = asset?.get("browser_download_url").toString()
    val fileName = downloadUrl.substringAfterLast("/")
    val jarFile = File(buildDir, "platform-libs/$fileName")

    if (!jarFile.exists()) {
        println("Downloading $fileName for $platform...")
        jarFile.parentFile.mkdirs()
        jarFile.writeBytes(URL(downloadUrl).readBytes())
    }

    return jarFile
}


val darwinJar by lazy { fetchJarFromRelease("darwin") }
val linuxJar by lazy { fetchJarFromRelease("linux") }
val windowsJar by lazy { fetchJarFromRelease("windows") }


afterEvaluate {
    addJarDependency(darwinJar)
    addJarDependency(linuxJar)
    addJarDependency(windowsJar)
}


tasks.test {
    useJUnitPlatform()
}

tasks.register<Jar>("darwinJar") {
    archiveClassifier.set("darwin")
    from(sourceSets["main"].output)
    from(darwinJar)
}

tasks.register<Jar>("linuxJar") {
    archiveClassifier.set("linux")
    from(sourceSets["main"].output)
    from(linuxJar)
}

tasks.register<Jar>("windowsJar") {
    archiveClassifier.set("windows")
    from(sourceSets["main"].output)
    from(windowsJar)
}

tasks.register<Delete>("deleteSpecificFile") {
    // Set the file you want to delete
    delete(file("build/libs/glass-kotlin-${version}.jar"))
}


tasks.named<Jar>("jar") {
    dependsOn("darwinJar", "linuxJar", "windowsJar")
    finalizedBy("deleteSpecificFile")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}



application {
    mainClass.set("com.thedevjade.glass.MainKt")
}
