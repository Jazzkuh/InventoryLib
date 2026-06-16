plugins {
    `java-library`
}

dependencies {
    api(project(":common"))

    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
}
