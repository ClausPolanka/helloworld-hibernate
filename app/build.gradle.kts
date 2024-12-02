plugins {
    kotlin("jvm") version "2.1.0-RC"
    application
    kotlin("plugin.jpa") version "2.1.0-RC"
    id("com.github.ben-manes.versions") version "0.51.0"
    idea
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // My dependencies
    implementation("org.hibernate:hibernate-core:7.0.0.Beta2")
    implementation("org.postgresql:postgresql:42.7.4")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.3")
}

application {
    mainClass.set("helloworldhibernate.AppKt")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
