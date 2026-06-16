subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    group = "com.jazzkuh.inventorylib"
    version = "1.1-SNAPSHOT"

    extensions.configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(25)
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        // Target Java 21 bytecode for the modules shaded into Paper plugins so they
        // load on Java 21+ servers. Other platforms (e.g. minestom) need Java 25.
        if (project.name in listOf("spigot", "common")) {
            options.release.set(21)
        }
        options.compilerArgs.add("-parameters")
    }

    dependencies {
        "compileOnly"("org.projectlombok:lombok:1.18.42")
        "annotationProcessor"("org.projectlombok:lombok:1.18.42")
        "compileOnly"("net.kyori:adventure-text-minimessage:5.1.1")
    }

    extensions.configure<PublishingExtension> {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }
}
