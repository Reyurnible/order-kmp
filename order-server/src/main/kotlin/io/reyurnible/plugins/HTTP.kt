package io.reyurnible.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureOpenApi() {
    routing {
        openAPI(path = "openapi")
    }
}
