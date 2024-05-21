package io.reyurnible.api.endpoints.users

import kotlinx.serialization.Serializable

@Serializable
data class CommonUserResponse(
    val id: String,
    val name: String,
    val age: Int,
)