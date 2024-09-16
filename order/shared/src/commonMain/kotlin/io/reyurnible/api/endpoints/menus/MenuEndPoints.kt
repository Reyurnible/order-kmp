package io.reyurnible.api.endpoints.menus

interface MenuEndPoints {
    suspend fun getList(): List<MenuResponse>
    suspend fun get(id: String): MenuResponse?
}