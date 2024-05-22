package io.reyurnible.order.routes.api

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.reyurnible.order.routes.api.users.usersApi

fun Route.api() {
    usersApi()
}
