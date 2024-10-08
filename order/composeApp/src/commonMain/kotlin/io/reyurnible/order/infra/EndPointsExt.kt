package io.reyurnible.order.infra

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import io.reyurnible.api.endpoints.EndPoints

suspend inline fun <reified T> EndPoints.handlingRequest(request: () -> HttpResponse): T {
    val response = request()
    if (response.status.isSuccess()) {
        return response.body<T>()
    } else {
        // Handling Error Response
        throw Exception("Failed to create user")
    }
}