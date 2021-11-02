import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/public")
    maven("https://jitpack.io")
    mavenCentral()
}
buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
    }
}
plugins {
    kotlin("jvm") version "1.5.10"
    application
    `kotlin-dsl`
    "com.github.dcendents.android-maven"
}
group = "fei.upload.apk"
version = "1.0.0"

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

application {

}