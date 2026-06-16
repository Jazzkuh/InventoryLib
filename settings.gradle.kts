plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "inventorylib"

include("common", "minestom", "spigot")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://repo.hypera.dev/snapshots")
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
