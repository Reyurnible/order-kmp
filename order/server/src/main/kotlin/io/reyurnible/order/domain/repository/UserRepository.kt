package io.reyurnible.order.domain.repository

import io.reyurnible.order.domain.model.User
import io.reyurnible.order.domain.model.UserId

interface UserRepository {
    suspend fun create(name: String, age: Int): User

    suspend fun getAll(): List<User>

    suspend fun get(id: UserId): User?

    suspend fun update(id: UserId, name: String, age: Int)

    suspend fun delete(id: UserId)
}