package five.head.core.data.data_source.network

import five.head.core.data.model.profile.UpdateProfileUser
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class ProfileUserApi(
    private val authClient: HttpClient,
){
    suspend fun getProfile() = authClient
        .get("/api/profile")
    suspend fun updateProfile(updateProfileUser: UpdateProfileUser) = authClient
        .put("/api/profile"){
            contentType(ContentType.Application.Json)
            setBody(updateProfileUser)
        }
}