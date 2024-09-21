package io.reyurnible.order.di

import io.ktor.client.HttpClient
import io.reyurnible.api.endpoints.menus.MenuEndPoints
import io.reyurnible.api.endpoints.users.UserEndPoints
import io.reyurnible.order.infra.ClientMockMenuEndpoints
import io.reyurnible.order.infra.ClientUserEndPoints
import io.reyurnible.order.infra.HttpClientFactory
import org.koin.dsl.module

val endPointsModule = module {
    single<HttpClient> { HttpClientFactory.create() }
    single<MenuEndPoints> {
        // ClientMenuEndpoints()
        ClientMockMenuEndpoints()
    }
    single<UserEndPoints> { ClientUserEndPoints(get<HttpClient>()) }
}