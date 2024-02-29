// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

/**
 * Strong skipping mode is an experimental feature in the Jetpack Compose Compiler 1.5.4+ that is currently being tested.
 */
//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>() {
//    compilerOptions.freeCompilerArgs.addAll(
//        "-P",
//        "plugin:androidx.compose.compiler.plugins.kotlin:experimentalStrongSkipping=true",
//    )
//}