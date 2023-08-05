import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
}

group = "com.vkuzel"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.squareup.okhttp3:okhttp:4.11.0")
}
