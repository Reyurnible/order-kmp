package io.reyurnible.routes.users

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserParams(
    val name: String,
    val age: Int,
)