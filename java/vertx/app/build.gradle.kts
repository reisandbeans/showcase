import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    id("polyglot.java-conventions")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

repositories {
    mavenCentral()
}

val mainVerticleName = "reis.polyglot.vertx.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"
val watchForChange = "app/src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
    // Define the main class for the application.
    mainClass.set(launcherClassName)
}

dependencies {
    implementation(Dependencies.logback)
    implementation(Dependencies.guava)
    implementation(Dependencies.guice)
    implementation(platform(Dependencies.vertxDepChain))
    implementation(Dependencies.vertxConfig)
    implementation(Dependencies.vertxCore)
    implementation(Dependencies.vertxRxJava)
    implementation(Dependencies.vertxWeb)
    implementation(Dependencies.vertxWebClient)
    implementation(project(":lib"))
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.mockitoInline)
    testImplementation(TestDependencies.mockitoJunit)
    testImplementation(TestDependencies.vertxJunit)
    testImplementation(TestDependencies.junitJupiter)
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("app")
    archiveClassifier.set("")
    manifest {
        attributes(mapOf("Main-Verticle" to mainVerticleName))
    }
    mergeServiceFiles()
}

tasks.withType<JavaExec> {
    args = listOf(
        "run",
        mainVerticleName,
        "--redeploy=$watchForChange",
        "--launcher-class=$launcherClassName",
        "--on-redeploy=$doOnChange"
    )
}
