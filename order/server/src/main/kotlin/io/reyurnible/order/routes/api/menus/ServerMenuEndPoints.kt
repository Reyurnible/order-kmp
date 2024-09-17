package io.reyurnible.order.routes.api.menus

import io.reyurnible.api.endpoints.menus.MenuEndPoints
import io.reyurnible.api.endpoints.menus.MenuResponse

class ServerMenuEndPoints : MenuEndPoints {
    override suspend fun getList(): List<MenuResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: String): MenuResponse? {
        TODO("Not yet implemented")
    }

}