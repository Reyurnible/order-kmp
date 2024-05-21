package io.reyurnible.order.infra.database.table

import org.jetbrains.exposed.sql.Table

object UsersTable : Table("Users") {
    val id = uuid("id")
    val name = varchar("name", length = 50)
    val age = integer("age")

    override val primaryKey = PrimaryKey(id)
}