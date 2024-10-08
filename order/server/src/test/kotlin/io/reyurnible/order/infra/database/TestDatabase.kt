package io.reyurnible.order.infra.database

import org.jetbrains.exposed.sql.Database

val testDatabase = Database.connect(
    url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
    user = "root",
    driver = "org.h2.Driver",
    password = ""
)