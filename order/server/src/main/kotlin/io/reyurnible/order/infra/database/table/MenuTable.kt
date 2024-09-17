package io.reyurnible.order.infra.database.table

import org.jetbrains.exposed.sql.Table

object MenuTable : Table("Menus") {
    val id = uuid("id")
    val name = varchar("name", length = 50)
    val description = varchar("description", length = 2000)
    val price = integer("price")
    val imageUrl = varchar("imageUrl", length = 2000).nullable()
    val calorie = integer("calorie").nullable()
    val allergy = varchar("allergy", length = 200).nullable()

    override val primaryKey = PrimaryKey(id)
}