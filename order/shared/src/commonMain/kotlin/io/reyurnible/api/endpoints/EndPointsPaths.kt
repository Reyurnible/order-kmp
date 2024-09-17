package io.reyurnible.api.endpoints

annotation class EndPoint(val method: ApiHttpMethod, val path: String)

enum class ApiHttpMethod {
    GET,
    POST,
    PUT,
    DELETE,
}