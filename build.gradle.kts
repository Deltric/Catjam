plugins {
    kotlin("jvm") version "1.7.10"
}

group = "dev.deltric"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.dv8tion:JDA:5.0.0-alpha.20")
    implementation("com.google.code.gson:gson:2.9.1")
}