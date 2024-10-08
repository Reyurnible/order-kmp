package io.reyurnible.api.endpoints.users

import io.reyurnible.api.endpoints.ApiHttpMethod
import io.reyurnible.api.endpoints.EndPoint
import io.reyurnible.api.endpoints.EndPoints

interface UserEndPoints : EndPoints {
    @EndPoint(ApiHttpMethod.POST, "/api/users")
    suspend fun post(params: CreateUserParams): CommonUserResponse

    @EndPoint(ApiHttpMethod.GET, "/api/users")
    suspend fun getList(): List<CommonUserResponse>

    @EndPoint(ApiHttpMethod.GET, "/api/users/{id}")
    suspend fun get(id: String): CommonUserResponse?

    @EndPoint(ApiHttpMethod.PUT, "/api/users/{id}")
    suspend fun put(id: String, params: UpdateUserParams)

    @EndPoint(ApiHttpMethod.DELETE, "/api/users/{id}")
    suspend fun delete(id: String)
}