package io.reyurnible.routes.users

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.reyurnible.domain.UserRepository
import io.reyurnible.domain.UserRepositoryImpl
import io.reyurnible.domain.model.User
import io.reyurnible.domain.model.UserId
import io.reyurnible.infra.database.database

fun Route.usersApi(
    userRepository: UserRepository = UserRepositoryImpl(database)
) {
    val userEndPoint = ServerUserEndPoints(userRepository)
    // Create user
    post("/users") {
        val result = userEndPoint.post(params = call.receive<CreateUserParams>())
        call.respond(HttpStatusCode.Created, result)
    }

    get("/users") {
        val result = userEndPoint.getList()
        call.respond(result)
    }

    // Read user
    get("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        val result = userEndPoint.get(id)
        if (result != null) {
            call.respond(HttpStatusCode.OK, result)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    // Update user
    put("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        userEndPoint.put(id, call.receive<UpdateUserParams>())
        call.respond(HttpStatusCode.OK)
    }

    // Delete user
    delete("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        userEndPoint.delete(id)
        call.respond(HttpStatusCode.OK)
    }
}

class ServerUserEndPoints(
    private val userRepository: UserRepository = UserRepositoryImpl(database)
) : UserEndPoints {
    override suspend fun post(params: CreateUserParams): User {
        return userRepository.create(params.name, params.age)
    }

    override suspend fun getList(): List<User> {
        return userRepository.getAll()
    }

    override suspend fun get(id: String): User? {
        return userRepository.get(UserId(id))
    }

    override suspend  fun put(id: String, params: UpdateUserParams) {
        userRepository.update(UserId(id), params.name, params.age)
    }

    override suspend fun delete(id: String) {
        userRepository.delete(UserId(id))
    }

}