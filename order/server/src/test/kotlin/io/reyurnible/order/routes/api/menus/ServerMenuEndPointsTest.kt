package io.reyurnible.order.routes.api.menus

import io.reyurnible.api.endpoints.menus.MenuResponse
import io.reyurnible.order.domain.repository.MenuRepository
import io.reyurnible.order.domain.repository.MockMenuRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import kotlin.test.Test

class ServerMenuEndPointsTest {
    private val mockMenuRepository: MenuRepository = MockMenuRepository()
    private lateinit var serverMenuEndPoints: ServerMenuEndPoints

    @Test
    fun getList_getSomeValue() = runTest {
        serverMenuEndPoints = ServerMenuEndPoints(mockMenuRepository)
        assertEquals(
            listOf(
                MenuResponse(
                    id = "test",
                    name = "test",
                    description = "test",
                    price = 100,
                    imageUrl = "test",
                    calorie = 100,
                    allergy = "test"
                )
            ),
            serverMenuEndPoints.getList()
        )

    }

    @Test
    fun get_getSomeValue() = runTest {
        serverMenuEndPoints = ServerMenuEndPoints(mockMenuRepository)
        assertEquals(
            MenuResponse(
                id = "test",
                name = "test",
                description = "test",
                price = 100,
                imageUrl = "test",
                calorie = 100,
                allergy = "test"
            ),
            serverMenuEndPoints.get("test")
        )
    }
}