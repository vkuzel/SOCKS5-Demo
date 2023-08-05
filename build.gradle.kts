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
	implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")
	implementation("com.squareup.okhttp3:okhttp:4.11.0")
}
