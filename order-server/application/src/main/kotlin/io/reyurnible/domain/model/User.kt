package io.reyurnible.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: UserId,
    val name: String,
    val age: Int,
)

@Serializable
@JvmInline
value class UserId(val rawValue: String)