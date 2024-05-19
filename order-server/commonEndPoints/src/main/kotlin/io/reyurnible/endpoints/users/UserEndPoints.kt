package io.reyurnible.endpoints.users

interface UserEndPoints {
    suspend fun post(params: CreateUserParams): CommonUserResponse
    suspend fun getList(): List<CommonUserResponse>
    suspend fun get(id: String): CommonUserResponse?
    suspend fun put(id: String, params: UpdateUserParams)
    suspend fun delete(id: String)
}