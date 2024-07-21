package io.reyurnible.order.infra

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient =
    HttpClient(CIO) {
        config(this)
    }

actual val LOCAL_HOST_BASE_URL: String = "http://localhost:8080"