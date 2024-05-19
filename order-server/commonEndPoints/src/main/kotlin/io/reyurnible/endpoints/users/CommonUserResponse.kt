package io.reyurnible.endpoints.users

import kotlinx.serialization.Serializable

@Serializable
data class CommonUserResponse(
    val id: String,
    val name: String,
    val age: Int,
)