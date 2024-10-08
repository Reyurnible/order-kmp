package io.reyurnible.order.domain.model

/**
 * Menu Entity
 *
 * @property id メニューID
 * @property name メニュー名
 * @property description メニュー説明
 * @property price 値段
 * @property imageUrl メニュー画像
 * @property calorie カロリー
 * @property allergy アレルギー
 */
data class Menu(
    val id: MenuId,
    val name: String,
    val description: String,
    val price: Int,
    val imageUrl: String?,
    val calorie: Int?,
    val allergy: String?,
)