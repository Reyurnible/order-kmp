package io.reyurnible.routes.users

import io.reyurnible.domain.model.User

interface UserEndPoints {
    suspend fun post(params: CreateUserParams): User
    suspend fun getList(): List<User>
    suspend fun get(id: String): User?
    suspend fun put(id: String, params: UpdateUserParams)
    suspend fun delete(id: String)
}