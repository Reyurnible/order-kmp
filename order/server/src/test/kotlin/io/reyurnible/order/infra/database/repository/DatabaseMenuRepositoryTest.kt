package io.reyurnible.order.infra.database.repository

import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId
import io.reyurnible.order.infra.database.table.MenusTable
import io.reyurnible.order.infra.database.testDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.UUID
import kotlin.test.assertEquals

class DatabaseMenuRepositoryTest {
    private lateinit var databaseMenuRepository: DatabaseMenuRepository

    @Before
    fun setUp() {
        databaseMenuRepository = DatabaseMenuRepository(testDatabase)
    }

    @After
    fun tearDown() = runTest {
        newSuspendedTransaction(Dispatchers.IO) {
            MenusTable.deleteAll()
        }

    }

    @Test
    fun test_create() = runTest {
        assertEquals(0, countTableContent())
        databaseMenuRepository.create(
            name = "name",
            description = "description",
            price = 100,
            imageUrl = "imageUrl",
            calorie = 100,
            allergy = "allergy"
        )
        assertEquals(1, countTableContent())
    }

    @Test
    fun test_getAll() = runTest {
        assertEquals(0, databaseMenuRepository.getAll().size)
        val createId = transaction {
            MenusTable.insert {
                it[id] = UUID.randomUUID()
                it[name] = "name"
                it[description] = "description"
                it[price] = 100
                it[imageUrl] = "imageUrl"
                it[calorie] = 100
                it[allergy] = "allergy"
            }[MenusTable.id].toString()
        }
        assertEquals(1, databaseMenuRepository.getAll().size)
        assertEquals(
            Menu(
                id = MenuId(createId),
                name = "name",
                description = "description",
                price = 100,
                imageUrl = "imageUrl",
                calorie = 100,
                allergy = "allergy"
            ), databaseMenuRepository.getAll().first()
        )
    }

    @Test
    fun test_get() = runTest {
        val createId = transaction {
            MenusTable.insert {
                it[id] = UUID.randomUUID()
                it[name] = "name"
                it[description] = "description"
                it[price] = 100
                it[imageUrl] = null
                it[calorie] = null
                it[allergy] = null
            }[MenusTable.id].toString()
        }
        assertEquals(
            Menu(
                id = MenuId(createId),
                name = "name",
                description = "description",
                price = 100,
                imageUrl = null,
                calorie = null,
                allergy = null,
            ), databaseMenuRepository.get(MenuId(createId))
        )
    }

    @Test
    fun test_update() = runTest {
        val createId = transaction {
            MenusTable.insert {
                it[id] = UUID.randomUUID()
                it[name] = "name"
                it[description] = "description"
                it[price] = 100
                it[imageUrl] = null
                it[calorie] = null
                it[allergy] = null
            }[MenusTable.id].toString()
        }
        databaseMenuRepository.update(
            id = MenuId(createId),
            name = "updateName",
            description = "updateDescription",
            price = 200,
            imageUrl = "updateImageUrl",
            calorie = 200,
            allergy = "updateAllergy"
        )
        assertEquals(
            Menu(
                id = MenuId(createId),
                name = "updateName",
                description = "updateDescription",
                price = 200,
                imageUrl = "updateImageUrl",
                calorie = 200,
                allergy = "updateAllergy"
            ), databaseMenuRepository.get(MenuId(createId))
        )
    }

    @Test
    fun test_delete() = runTest {
        val createId = transaction {
            MenusTable.insert {
                it[id] = UUID.randomUUID()
                it[name] = "name"
                it[description] = "description"
                it[price] = 100
                it[imageUrl] = null
                it[calorie] = null
                it[allergy] = null
            }[MenusTable.id].toString()
        }
        assertEquals(1, countTableContent())
        databaseMenuRepository.delete(MenuId(createId))
        assertEquals(0, countTableContent())
    }

    private fun countTableContent() =
        transaction {
            MenusTable.selectAll().count()
        }
}