package io.reyurnible.order.domain.model

data class Menu(
    val id: MenuId,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String?,
    val calorie: Int?,
    val allergy: String?,
)