package io.reyurnible.order.routes.api.menus

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.reyurnible.order.domain.repository.MenuRepository
import io.reyurnible.order.infra.database.database
import io.reyurnible.order.infra.database.repository.DatabaseMenuRepository

fun Route.menusApi(
    menuRepository: MenuRepository = DatabaseMenuRepository(database),
) {
    val endPoint = ServerMenuEndPoints(menuRepository)

    get("/menus") {
        val result = endPoint.getList()
        call.respond(result)
    }

    get("/menus/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        val result = endPoint.get(id)
        if (result != null) {
            call.respond(HttpStatusCode.OK, result)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}