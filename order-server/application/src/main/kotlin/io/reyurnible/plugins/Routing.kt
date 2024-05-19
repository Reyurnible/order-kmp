package io.reyurnible.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.reyurnible.routes.users.usersApi

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        usersApi()
    }
}
