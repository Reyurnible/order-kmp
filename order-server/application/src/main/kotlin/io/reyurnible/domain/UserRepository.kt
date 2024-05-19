package io.reyurnible.domain

import io.reyurnible.domain.model.User
import io.reyurnible.domain.model.UserId
import io.reyurnible.infra.database.table.UsersTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

interface UserRepository {
    suspend fun create(name: String, age: Int): User

    suspend fun getAll(): List<User>

    suspend fun get(id: UserId): User?

    suspend fun update(id: UserId, name: String, age: Int)

    suspend fun delete(id: UserId)
}

class UserRepositoryImpl(private val database: Database) : UserRepository {
    init {
        transaction(database) {
            SchemaUtils.create(UsersTable)
        }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    override suspend fun create(name: String, age: Int): User = dbQuery {
        UsersTable.insert {
            it[UsersTable.id] = UUID.randomUUID()
            it[UsersTable.name] = name
            it[UsersTable.age] = age
        }.let {
            User(
                id = UserId(it[UsersTable.id].toString()),
                name = it[UsersTable.name],
                age = it[UsersTable.age]
            )
        }
    }

    override suspend fun getAll(): List<User> = dbQuery {
        UsersTable.selectAll()
            .map {
                User(
                    id = UserId(it[UsersTable.id].toString()),
                    name = it[UsersTable.name],
                    age = it[UsersTable.age]
                )
            }
    }

    override suspend fun get(id: UserId): User? = dbQuery {
        UsersTable.select { UsersTable.id eq UUID.fromString(id.rawValue) }
            .map {
                User(
                    id = UserId(it[UsersTable.id].toString()),
                    name = it[UsersTable.name],
                    age = it[UsersTable.age]
                )
            }
            .singleOrNull()
    }

    override suspend fun update(id: UserId, name: String, age: Int) {
        dbQuery {
            UsersTable.update({ UsersTable.id eq UUID.fromString(id.rawValue) }) {
                it[UsersTable.name] = name
                it[UsersTable.age] = age
            }
        }
    }

    override suspend fun delete(id: UserId) {
        dbQuery {
            UsersTable.deleteWhere { UsersTable.id eq UUID.fromString(id.rawValue) }
        }
    }

}