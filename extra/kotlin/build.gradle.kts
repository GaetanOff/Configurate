import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.spongepowered.configurate.build.core

plugins {
    kotlin("jvm")
    id("org.jetbrains.dokka")
    id("io.gitlab.arturbosch.detekt")
    id("org.spongepowered.configurate.build.component")
    id("org.jlleitschuh.gradle.ktlint")
}

configurations.matching { it.name.startsWith("dokka") && it.name.endsWith("Plugin") }.configureEach {
    // Appears the configuration can't be resolved?
    // thanks Kotlin
    resolutionStrategy.deactivateDependencyLocking()
}

tasks.withType(KotlinCompile::class).configureEach {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += listOf("-Xopt-in=kotlin.RequiresOptIn", "-Xemit-jvm-type-annotations")
}

tasks.javadocJar.configure {
    from(tasks.dokkaJavadoc.map { it.outputs })
}

tasks.withType(DokkaTask::class).configureEach {
    moduleName.set("configurate-${project.name}")
    dokkaSourceSets.configureEach {
        includes.from(file("src/main/packages.md"))
        jdkVersion.set(8)
        reportUndocumented.set(true)
    }
}

tasks.withType(Detekt::class).configureEach {
    config.setFrom(rootProject.file(".detekt/detekt.yml"))
}

dependencies {
    api(core())
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    // This version has to be kept in sync with the Kotlin plugin version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
}