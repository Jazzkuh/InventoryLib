plugins {
    `java-library`
}

dependencies {
    api(project(":common"))

    compileOnly("net.minestom:minestom:2026.05.17c-26.1.1")
    compileOnly("net.kyori:adventure-api:5.1.1")
}
