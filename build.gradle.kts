import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/public")
    maven("https://jitpack.io")
    mavenCentral()
}
plugins {
    kotlin("jvm") version "1.5.10"
    `kotlin-dsl`
    `maven-publish`
}
group = "fei.upload.apk"
version = "1.0.3"

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.google.code.gson:gson:2.8.8")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}