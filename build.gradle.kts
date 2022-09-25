plugins {
    this.kotlin("jvm") version "1.7.10"
}

group = "dev.deltric"
version = "1.0-SNAPSHOT"

repositories {
    this.mavenCentral()
    this.maven(url = "https://jitpack.io")
}

dependencies {
    this.implementation(this.kotlin("stdlib"))
    this.implementation("net.dv8tion:JDA:5.0.0-alpha.20")
    this.implementation("com.google.code.gson:gson:2.9.1")
    this.implementation("ch.qos.logback:logback-classic:1.2.8")
}