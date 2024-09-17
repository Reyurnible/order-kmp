package io.reyurnible.order.routes.api.menus

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import kotlin.test.Test

class ServerMenuEndPointsTest {
    private lateinit var serverMenuEndPoints: ServerMenuEndPoints

    @Test
    fun getList_getSomeValue(): Unit = runBlocking {
        serverMenuEndPoints = ServerMenuEndPoints()
        serverMenuEndPoints.getList()
    }

    @Test
    fun get_getSomeValue(): Unit = runBlocking {
        serverMenuEndPoints = ServerMenuEndPoints()
        serverMenuEndPoints.get("test")
    }
}