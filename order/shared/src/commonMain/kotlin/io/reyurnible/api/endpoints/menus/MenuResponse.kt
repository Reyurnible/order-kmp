package io.reyurnible.api.endpoints.menus

import kotlinx.serialization.Serializable

/**
 * Menu Response
 *
 * @param id Menu Id
 * @param name Menu Name
 * @param description Menu Description
 * @param price Menu Price. Minimum value is 0. Unit is JPY.
 * @param imageUrl Menu Image URL
 * @param calorie Menu Calorie
 * @param allergy Menu Allergy
 */
@Serializable
data class MenuResponse(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String?,
    val calorie: String?,
    val allergy: String?,
)