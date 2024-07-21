package io.reyurnible.order.infra

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.js.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient =
    HttpClient(Js) {
        config(this)
    }

actual val LOCAL_HOST_BASE_URL: String = "http://localhost:8080"