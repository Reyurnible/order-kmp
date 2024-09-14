package io.reyurnible.order.domain

import io.reyurnible.order.ui.routes.select_item.ItemId

data class Item(
    val id: ItemId,
    val name: String,
    val price: Int,
    val imageUrl: String,
)