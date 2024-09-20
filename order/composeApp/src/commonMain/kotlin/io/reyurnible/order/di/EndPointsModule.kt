package io.reyurnible.order.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.reyurnible.order.infra.ClientMenuEndpoints
import io.reyurnible.order.infra.ClientUserEndPoints
import io.reyurnible.order.infra.HttpClientFactory
import io.reyurnible.order.infra.httpClient
import kotlinx.serialization.json.Json
import org.koin.compose.koinInject
import org.koin.dsl.module

val endPointsModule = module {
    single<HttpClient> { HttpClientFactory.create() }
    single { ClientMenuEndpoints() }
    single { ClientUserEndPoints(get<HttpClient>()) }
}