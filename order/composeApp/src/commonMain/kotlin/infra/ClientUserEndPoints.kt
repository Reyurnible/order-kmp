package infra

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.reyurnible.api.endpoints.users.CommonUserResponse
import io.reyurnible.api.endpoints.users.CreateUserParams
import io.reyurnible.api.endpoints.users.UpdateUserParams
import io.reyurnible.api.endpoints.users.UserEndPoints
import kotlinx.serialization.json.Json

class ClientUserEndPoints(
    // TODO : Add DI for HttpClient by Koin
    private val httpClient: HttpClient = httpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = false
                },
            )
        }
    }
) : UserEndPoints {
    companion object {
        private val BASE_URL = LOCAL_HOST_BASE_URL
    }

    override suspend fun post(params: CreateUserParams): CommonUserResponse =
        handlingRequest<CommonUserResponse> {
            httpClient.post("$BASE_URL/users") {
                contentType(ContentType.Application.Json)
                setBody<CreateUserParams>(params)
            }
        }

    override suspend fun getList(): List<CommonUserResponse> =
        handlingRequest<List<CommonUserResponse>> {
            httpClient.get("$BASE_URL/users")
        }

    override suspend fun get(id: String): CommonUserResponse? =
        handlingRequest<CommonUserResponse?> {
            httpClient.get("$BASE_URL/users/$id")
        }

    override suspend fun put(id: String, params: UpdateUserParams) =
        handlingRequest<Unit> {
            httpClient.put("$BASE_URL/users/$id") {
                contentType(ContentType.Application.Json)
                setBody<UpdateUserParams>(params)
            }
        }

    override suspend fun delete(id: String) =
        handlingRequest<Unit> {
            httpClient.delete("$BASE_URL/users/$id")
        }

    private suspend inline fun <reified T> handlingRequest(request: () -> HttpResponse): T {
        val response = request()
        if (response.status.isSuccess()) {
            return response.body<T>()
        } else {
            // Handling Error Response
            throw Exception("Failed to create user")
        }
    }

}