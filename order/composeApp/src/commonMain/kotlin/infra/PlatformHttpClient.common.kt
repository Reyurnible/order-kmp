package infra

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

expect val LOCAL_HOST_BASE_URL: String