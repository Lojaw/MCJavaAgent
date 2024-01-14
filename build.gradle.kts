plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.bytebuddy:byte-buddy:1.14.11") // Ersetzen Sie dies durch die neueste verfügbare Version
    implementation("net.bytebuddy:byte-buddy-agent:1.14.11")
    implementation("org.lwjgl:lwjgl:3.3.2")
    implementation("org.lwjgl:lwjgl-opengl:3.3.2")
}

tasks {
    shadowJar {
        manifest {
            attributes(
                    "Premain-Class" to "de.lojaw.MyJavaAgent",
                    "Can-Redefine-Classes" to "true",
                    "Can-Retransform-Classes" to "true"
            )
        }
    }

    test {
        useJUnitPlatform()
    }
}