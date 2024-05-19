plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "io.reyurnible.order-server"
include("application")
include("commonEndPoints")
