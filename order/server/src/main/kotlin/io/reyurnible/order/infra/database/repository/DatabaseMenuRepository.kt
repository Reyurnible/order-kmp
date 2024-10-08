package io.reyurnible.order.infra.database.repository

import io.reyurnible.order.domain.repository.MenuRepository
import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId
import io.reyurnible.order.infra.database.table.MenusTable
import io.reyurnible.order.infra.database.table.UsersTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.UUID

class DatabaseMenuRepository(private val database: Database) : MenuRepository {
    init {
        transaction(database) {
            SchemaUtils.create(MenusTable)
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    override suspend fun create(
        name: String,
        description: String,
        price: Int,
        imageUrl: String,
        calorie: Int,
        allergy: String
    ): Menu = dbQuery {
        MenusTable.insert {
            it[id] = UUID.randomUUID()
            it[MenusTable.name] = name
            it[MenusTable.description] = description
            it[MenusTable.price] = price
            it[MenusTable.imageUrl] = imageUrl
            it[MenusTable.calorie] = calorie
            it[MenusTable.allergy] = allergy
        }.let {
            Menu(
                id = MenuId(it[MenusTable.id].toString()),
                name = it[MenusTable.name],
                description = it[MenusTable.description],
                price = it[MenusTable.price],
                imageUrl = it[MenusTable.imageUrl],
                calorie = it[MenusTable.calorie],
                allergy = it[MenusTable.allergy]
            )
        }
    }

    override suspend fun getAll(): List<Menu> = dbQuery {
        MenusTable.selectAll().map {
            Menu(
                id = MenuId(it[MenusTable.id].toString()),
                name = it[MenusTable.name],
                description = it[MenusTable.description],
                price = it[MenusTable.price],
                imageUrl = it[MenusTable.imageUrl],
                calorie = it[MenusTable.calorie],
                allergy = it[MenusTable.allergy]
            )
        }
    }

    override suspend fun get(id: MenuId): Menu? = dbQuery {
        MenusTable
            .select { MenusTable.id eq UUID.fromString(id.rawValue) }
            .map {
                Menu(
                    id = MenuId(it[MenusTable.id].toString()),
                    name = it[MenusTable.name],
                    description = it[MenusTable.description],
                    price = it[MenusTable.price],
                    imageUrl = it[MenusTable.imageUrl],
                    calorie = it[MenusTable.calorie],
                    allergy = it[MenusTable.allergy]
                )
            }
            .singleOrNull()
    }

    override suspend fun update(
        id: MenuId,
        name: String,
        description: String,
        price: Int,
        imageUrl: String,
        calorie: Int,
        allergy: String
    ): Unit = dbQuery {
        MenusTable.update({ MenusTable.id eq UUID.fromString(id.rawValue) }) {
            it[MenusTable.name] = name
            it[MenusTable.description] = description
            it[MenusTable.price] = price
            it[MenusTable.imageUrl] = imageUrl
            it[MenusTable.calorie] = calorie
            it[MenusTable.allergy] = allergy
        }
    }

    override suspend fun delete(id: MenuId): Unit = dbQuery {
        MenusTable.deleteWhere { MenusTable.id eq UUID.fromString(id.rawValue) }
    }

}