package io.reyurnible.order.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.reyurnible.order.routes.api.api

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        api()
    }
}
