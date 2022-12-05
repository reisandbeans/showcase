import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    java
    application
    jacoco
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

repositories {
    mavenCentral()
}

val vertxVersion = "4.3.5";
val junitJupiterVersion = "5.7.0"
val mainVerticleName = "reis.polyglot.vertx.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"
val watchForChange = "app/src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
    // Define the main class for the application.
    mainClass.set(launcherClassName)
}

dependencies {
    implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
    implementation("io.vertx:vertx-core")
    implementation("io.vertx:vertx-web")
    testImplementation("io.vertx:vertx-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("fat")
    manifest {
        attributes(mapOf("Main-Verticle" to mainVerticleName))
    }
    mergeServiceFiles()
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.named("jacocoTestReport"))
    testLogging {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}

tasks.named("check").configure {
    dependsOn("jacocoTestCoverageVerification")
}

tasks.withType<JacocoReport> {
    dependsOn(tasks.test)
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.95".toBigDecimal()
            }
        }
    }
}

tasks.withType<JavaExec> {
    args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
