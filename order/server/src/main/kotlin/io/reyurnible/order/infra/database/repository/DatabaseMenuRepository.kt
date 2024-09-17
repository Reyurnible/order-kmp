package io.reyurnible.order.infra.database.repository

import io.reyurnible.order.domain.repository.MenuRepository
import io.reyurnible.order.domain.model.Menu
import io.reyurnible.order.domain.model.MenuId
import io.reyurnible.order.infra.database.table.MenuTable
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
            SchemaUtils.create(UsersTable)
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
        MenuTable.insert {
            it[id] = UUID.randomUUID()
            it[MenuTable.name] = name
            it[MenuTable.description] = description
            it[MenuTable.price] = price
            it[MenuTable.imageUrl] = imageUrl
            it[MenuTable.calorie] = calorie
            it[MenuTable.allergy] = allergy
        }.let {
            Menu(
                id = MenuId(it[MenuTable.id].toString()),
                name = it[MenuTable.name],
                description = it[MenuTable.description],
                price = it[MenuTable.price],
                imageUrl = it[MenuTable.imageUrl],
                calorie = it[MenuTable.calorie],
                allergy = it[MenuTable.allergy]
            )
        }
    }

    override suspend fun getAll(): List<Menu> = dbQuery {
        MenuTable.selectAll().map {
            Menu(
                id = MenuId(it[MenuTable.id].toString()),
                name = it[MenuTable.name],
                description = it[MenuTable.description],
                price = it[MenuTable.price],
                imageUrl = it[MenuTable.imageUrl],
                calorie = it[MenuTable.calorie],
                allergy = it[MenuTable.allergy]
            )
        }
    }

    override suspend fun get(id: MenuId): Menu? = dbQuery {
        MenuTable
            .select { MenuTable.id eq UUID.fromString(id.rawValue) }
            .map {
                Menu(
                    id = MenuId(it[MenuTable.id].toString()),
                    name = it[MenuTable.name],
                    description = it[MenuTable.description],
                    price = it[MenuTable.price],
                    imageUrl = it[MenuTable.imageUrl],
                    calorie = it[MenuTable.calorie],
                    allergy = it[MenuTable.allergy]
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
    ) {
        dbQuery {
            MenuTable.update({ MenuTable.id eq UUID.fromString(id.rawValue) }) {
                it[MenuTable.name] = name
                it[MenuTable.description] = description
                it[MenuTable.price] = price
                it[MenuTable.imageUrl] = imageUrl
                it[MenuTable.calorie] = calorie
                it[MenuTable.allergy] = allergy
            }
        }
    }

    override suspend fun delete(id: MenuId) {
        dbQuery {
            MenuTable.deleteWhere { MenuTable.id eq UUID.fromString(id.rawValue) }
        }
    }

}