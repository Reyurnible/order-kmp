package infra

import io.reyurnible.api.endpoints.users.CommonUserResponse
import io.reyurnible.api.endpoints.users.CreateUserParams
import io.reyurnible.api.endpoints.users.UpdateUserParams
import io.reyurnible.api.endpoints.users.UserEndPoints

class ClientUserEndPoints : UserEndPoints {
    override suspend fun post(params: CreateUserParams): CommonUserResponse {
        TODO("Not yet implemented")
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