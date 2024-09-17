package io.reyurnible.api.endpoints.menus

import io.reyurnible.api.endpoints.ApiHttpMethod
import io.reyurnible.api.endpoints.EndPoint

interface MenuEndPoints {
    @EndPoint(method = ApiHttpMethod.GET, path = "/menus")
    suspend fun getList(): List<MenuResponse>

    @EndPoint(method = ApiHttpMethod.GET, path = "/menus/{id}")
    suspend fun get(id: String): MenuResponse?
}