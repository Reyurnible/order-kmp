package io.reyurnible.routes.users

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.reyurnible.domain.UserRepository
import io.reyurnible.domain.UserRepositoryImpl
import io.reyurnible.domain.model.UserId
import io.reyurnible.infra.database.database

fun Route.usersApi(
    userRepository: UserRepository = UserRepositoryImpl(database)
) {
    // Create user
    post("/users") {
        val requestParam = call.receive<CreateUserParams>()
        val user = userRepository.create(requestParam.name, requestParam.age)
        println(user.id)
        call.respond(HttpStatusCode.Created, user)
    }

    get("/users") {
        val userList = userRepository.getAll()
        call.respond(userList)
    }

    // Read user
    get("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        val user = userRepository.get(UserId(id))
        if (user != null) {
            call.respond(HttpStatusCode.OK, user)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    // Update user
    put("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        val requestParam = call.receive<UpdateUserParams>()
        userRepository.update(UserId(id), requestParam.name, requestParam.age)
        call.respond(HttpStatusCode.OK)
    }

    // Delete user
    delete("/users/{id}") {
        val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
        userRepository.delete(UserId(id))
        call.respond(HttpStatusCode.OK)
    }

}