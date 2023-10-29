package five.head.core.data.data_source.network

import five.head.core.data.model.training.ResultTest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class TestApi(
    private val authClient: HttpClient
) {
    suspend fun getListTest() = authClient
        .get("/api/tests")
    suspend fun getTestData(id: String) = authClient
        .get("/api/test/$id")
    suspend fun saveResultTest(result: ResultTest) = authClient
        .post("/api/test/result"){
            setBody(result)
            contentType(ContentType.Application.Json)
        }
    suspend fun getDepartmentAnalytics(idDepartment: String) = authClient
        .get("/api/tests/analytic/$idDepartment")

    suspend fun getAnalyticsOnUserTests(idUser: String) = authClient
        .get("/api/tests/analytics/$idUser")
}