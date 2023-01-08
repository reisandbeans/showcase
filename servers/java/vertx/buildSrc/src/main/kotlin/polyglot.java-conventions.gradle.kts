import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    jacoco
    checkstyle
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

checkstyle {
    configFile = file("${project.rootDir}/checkstyle.xml")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.named("jacocoTestReport"))
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
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
