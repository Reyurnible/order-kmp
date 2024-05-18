package io.reyurnible.routes.users

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserParams(
    val name: String,
    val age: Int,
)