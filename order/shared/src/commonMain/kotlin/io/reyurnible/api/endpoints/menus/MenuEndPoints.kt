package io.reyurnible.api.endpoints.menus

import io.reyurnible.api.endpoints.ApiHttpMethod
import io.reyurnible.api.endpoints.EndPoint
import io.reyurnible.api.endpoints.EndPoints

interface MenuEndPoints : EndPoints {
    @EndPoint(method = ApiHttpMethod.GET, path = "/api/menus")
    suspend fun getList(): List<MenuResponse>

    @EndPoint(method = ApiHttpMethod.GET, path = "/api/menus/{id}")
    suspend fun get(id: String): MenuResponse?
}