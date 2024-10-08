package io.reyurnible.order.domain.repository

import io.reyurnible.api.endpoints.menus.MenuEndPoints
import io.reyurnible.api.endpoints.menus.MenuResponse
import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId

interface MenuRepository {
    suspend fun getList(): List<Menu>

    suspend fun get(id: MenuId): Menu?
}

class MenuRepositoryImpl(
    private val endPoints: MenuEndPoints
) : MenuRepository {
    override suspend fun getList(): List<Menu> =
        endPoints.getList().map { it.toDomainModel() }

    override suspend fun get(id: MenuId): Menu? =
        endPoints.get(id.rawValue)?.toDomainModel()

    private fun MenuResponse.toDomainModel(): Menu {
        return Menu(
            id = MenuId(id),
            name = name,
            description = description,
            price = price,
            imageUrl = imageUrl,
            calorie = calorie,
            allergy = allergy
        )
    }
}