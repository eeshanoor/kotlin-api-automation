plugins {
    kotlin("jvm") version "1.9.24"
}

group = "com.eeshanoor"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.4.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

kotlin {
    jvmToolchain(17)
}
