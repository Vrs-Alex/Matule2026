plugins {
	kotlin("jvm") version "2.2.0"
	kotlin("plugin.spring") version "2.2.0"
	kotlin("plugin.serialization") version "2.2.0"

	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"

}

group = "vrsalex"
version = "0.0.1"
description = " API for the Matule student project"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

    // Web Controllers ets
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Database
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly ("com.h2database:h2")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation ("com.fasterxml.jackson.core:jackson-annotations")

    implementation("org.springframework.boot:spring-boot-starter-security")


	// Jwt
	implementation("io.jsonwebtoken:jjwt-api:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

	// Kotlin Serialization
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.9.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.9.0")

	// Caffeine
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("com.github.ben-manes.caffeine:caffeine")

    implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
