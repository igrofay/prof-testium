package five.head.core.data.data_source.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class LecternApi(
    private val authClient: HttpClient
) {
    suspend fun getListLectern() = authClient
        .get("/api/lecterns")
}