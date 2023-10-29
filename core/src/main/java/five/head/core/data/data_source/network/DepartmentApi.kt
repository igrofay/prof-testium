package five.head.core.data.data_source.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class DepartmentApi(
    private val authClient: HttpClient,
) {
    suspend fun getDepartment() = authClient
        .get("/api/departments")
}