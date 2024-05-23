package infra

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
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
        private const val BASE_URL = "http://10.0.2.2:8080"
    }
    override suspend fun post(params: CreateUserParams): CommonUserResponse {
        val httpResponse = httpClient.post("$BASE_URL/users") {
            contentType(ContentType.Application.Json)
            setBody<CreateUserParams>(params)
        }
        if (httpResponse.status.isSuccess()) {
            return httpResponse.body<CommonUserResponse>()
        } else {
            // Handling Error Response
            throw Exception("Failed to create user")
        }
    }

    override suspend fun getList(): List<CommonUserResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: String): CommonUserResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun put(id: String, params: UpdateUserParams) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

}