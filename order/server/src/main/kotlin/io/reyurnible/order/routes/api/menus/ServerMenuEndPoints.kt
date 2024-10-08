package io.reyurnible.order.routes.api.menus

import io.reyurnible.api.endpoints.menus.MenuEndPoints
import io.reyurnible.api.endpoints.menus.MenuResponse
import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId
import io.reyurnible.order.domain.repository.MenuRepository
import java.util.UUID

class ServerMenuEndPoints(
    private val menuRepository: MenuRepository
) : MenuEndPoints {
    override suspend fun getList(): List<MenuResponse> =
        menuRepository.getAll().map { it.toResponse() }

    override suspend fun get(id: String): MenuResponse? =
        menuRepository.get(MenuId(id))?.toResponse()

    private fun Menu.toResponse(): MenuResponse = MenuResponse(
        id = id.rawValue,
        name = name,
        description = description,
        price = price,
        imageUrl = imageUrl,
        calorie = calorie,
        allergy = allergy
    )

}