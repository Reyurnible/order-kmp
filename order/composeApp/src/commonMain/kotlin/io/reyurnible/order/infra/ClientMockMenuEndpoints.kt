package io.reyurnible.order.infra

import io.reyurnible.api.endpoints.menus.MenuEndPoints
import io.reyurnible.api.endpoints.menus.MenuResponse

class ClientMockMenuEndpoints(

) : MenuEndPoints {
    override suspend fun getList(): List<MenuResponse> =
        listOf(
            MenuResponse(
                id = "demihamberg",
                name = "ハンバーグデミグラスソース",
                description = "のびやかに広がる豊かな自然で育った、でっかい道産牛を１００％使用したビーフハンバーグです。",
                price = 1599,
                imageUrl = "https://www.skylark.co.jp/jonathan/menu/json/menu/61_28839.jpg",
                calorie = 600,
                allergy = null,
            ),
            MenuResponse(
                id = "ponzuhamberg",
                name = "ハンバーグおろしポン酢",
                description = "のびやかに広がる豊かな自然で育った、でっかい道産牛を１００％使用したビーフハンバーグです。",
                price = 1599,
                imageUrl = "https://www.skylark.co.jp/jonathan/menu/json/menu/61_28839.jpg",
                calorie = 600,
                allergy = null,
            ),
        )

    override suspend fun get(id: String): MenuResponse? =
        MenuResponse(
            id = "1",
            name = "ハンバーグデミグラスソース",
            description = "のびやかに広がる豊かな自然で育った、でっかい道産牛を１００％使用したビーフハンバーグです。",
            price = 1599,
            imageUrl = "https://www.skylark.co.jp/jonathan/menu/json/menu/61_28839.jpg",
            calorie = 600,
            allergy = null,
        )
}