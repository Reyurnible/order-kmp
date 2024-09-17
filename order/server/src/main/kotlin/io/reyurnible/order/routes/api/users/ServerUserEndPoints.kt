package io.reyurnible.order.routes.api.users

import io.reyurnible.api.endpoints.users.CommonUserResponse
import io.reyurnible.api.endpoints.users.CreateUserParams
import io.reyurnible.api.endpoints.users.UpdateUserParams
import io.reyurnible.api.endpoints.users.UserEndPoints
import io.reyurnible.order.domain.repository.UserRepository
import io.reyurnible.order.domain.model.User
import io.reyurnible.order.domain.model.UserId

class ServerUserEndPoints(
    private val userRepository: UserRepository,
) : UserEndPoints {
    override suspend fun post(params: CreateUserParams): CommonUserResponse {
        return userRepository.create(params.name, params.age).toResponse()
    }

    override suspend fun getList(): List<CommonUserResponse> {
        return userRepository.getAll().map { it.toResponse() }
    }

    override suspend fun get(id: String): CommonUserResponse? {
        return userRepository.get(UserId(id))?.toResponse()
    }

    override suspend fun put(id: String, params: UpdateUserParams) {
        userRepository.update(UserId(id), params.name, params.age)
    }

    override suspend fun delete(id: String) {
        userRepository.delete(UserId(id))
    }
}

private fun User.toResponse(): CommonUserResponse =
    CommonUserResponse(
        id = id.rawValue,
        name = name,
        age = age,
    )