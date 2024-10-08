package io.reyurnible.order.infra

import io.reyurnible.api.endpoints.menus.MenuEndPoints
import io.reyurnible.api.endpoints.menus.MenuResponse

class ClientMenuEndpoints(

) : MenuEndPoints {
    override suspend fun getList(): List<MenuResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: String): MenuResponse? {
        TODO("Not yet implemented")
    }
}