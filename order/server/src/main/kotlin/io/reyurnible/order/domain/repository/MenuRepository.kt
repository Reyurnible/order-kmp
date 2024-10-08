package io.reyurnible.order.domain.repository

import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId

interface MenuRepository {
    suspend fun create(
        name: String,
        description: String,
        price: Int,
        imageUrl: String,
        calorie: Int,
        allergy: String,
    ): Menu

    suspend fun getAll(): List<Menu>

    suspend fun get(id: MenuId): Menu?

    suspend fun update(
        id: MenuId,
        name: String,
        description: String,
        price: Int,
        imageUrl: String,
        calorie: Int,
        allergy: String,
    )

    suspend fun delete(id: MenuId)
}