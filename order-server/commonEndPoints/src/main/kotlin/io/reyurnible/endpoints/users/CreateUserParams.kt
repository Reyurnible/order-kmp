package io.reyurnible.endpoints.users

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserParams(
    val name: String,
    val age: Int,
)