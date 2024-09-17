package io.reyurnible.order.domain.repository

import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId

class MockMenuRepository : MenuRepository {
    override suspend fun create(
        name: String,
        description: String,
        price: Int,
        imageUrl: String,
        calorie: Int,
        allergy: String
    ): Menu = Menu(
        MenuId("test"),
        name,
        description,
        price,
        imageUrl,
        calorie,
        allergy
    )

    override suspend fun getAll(): List<Menu> =
        listOf(
            Menu(
                MenuId("test"),
                "test",
                "test",
                100,
                "test",
                100,
                "test"
            )
        )

    override suspend fun get(id: MenuId): Menu? =
        if (id.rawValue == "test") {
            Menu(
                MenuId("test"),
                "test",
                "test",
                100,
                "test",
                100,
                "test"
            )
        } else {
            null
        }

    override suspend fun update(
        id: MenuId,
        name: String,
        description: String,
        price: Int,
        imageUrl: String,
        calorie: Int,
        allergy: String
    ) {
        // do nothing
    }

    override suspend fun delete(id: MenuId) {
        // do nothing
    }

}