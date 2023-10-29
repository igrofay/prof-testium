package five.head.core.data.data_source.network

import android.util.Log
import five.head.core.data.model.auth.SignInBody
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class AuthApi(
    private val baseClient: HttpClient,
) {
    suspend fun signIn(signInBody: SignInBody) = baseClient
        .post("/api/signin"){
            Log.e("AuthApi", signInBody.phone)
            setBody(signInBody)
            contentType(ContentType.Application.Json)
        }
}