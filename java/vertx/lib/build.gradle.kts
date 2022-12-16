plugins {
    id("java")
}

group = "reis.polyglot"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.vertxCore)
    implementation(Dependencies.vertxJsonSchema)
    implementation(platform(Dependencies.vertxDepChain))
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.mockitoJunit)
    testImplementation(TestDependencies.vertxJunit)
    testImplementation(TestDependencies.junitJupiter)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
