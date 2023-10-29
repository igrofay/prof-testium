package five.head.core.dependency_injection

import android.util.Log
import five.head.core.data.data_source.network.AuthApi
import five.head.core.data.data_source.network.DepartmentApi
import five.head.core.data.data_source.network.LecternApi
import five.head.core.data.data_source.network.ProfileUserApi
import five.head.core.data.data_source.network.TestApi
import five.head.core.domain.repos.UserRepos
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindConstant
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal const val BASE_CLIENT = "BASE_CLIENT"
internal const val AUTHORIZED_CLIENT = "AUTHORIZED_CLIENT"
const val URL_SERVER = "URL_SERVER"

internal val NetworkModule by DI.Module {
    bindConstant(URL_SERVER) { "http://192.168.0.101:8090/" }
    bindSingleton(BASE_CLIENT) {
        httpClient(instance(URL_SERVER))
            .apply {
                plugin(HttpSend).intercept { request ->
                    Log.d("$BASE_CLIENT::intercept::request", request.url.toString())
                    execute(request).apply {
                        Log.d(
                            "$BASE_CLIENT::intercept::answer",
                            this.request.url.toString() + " " + this.response.status.value.toString()
                        )
                    }
                }
            }
    }
    bindSingleton(AUTHORIZED_CLIENT) {
        httpClient(instance(URL_SERVER))
            .apply {
                plugin(HttpSend).intercept { request ->
                    Log.d("$AUTHORIZED_CLIENT::intercept::request", request.url.toString())
                    request.header(HttpHeaders.Authorization, instance<UserRepos>().accessToken)
                    execute(request).apply {
                        Log.d(
                            "$AUTHORIZED_CLIENT::intercept::answer",
                            this.request.url.toString() + " " + this.response.status.value.toString()
                        )
                    }
                }
            }
    }
    bindProvider {
        AuthApi(instance(BASE_CLIENT))
    }
    bindProvider {
        ProfileUserApi(instance(AUTHORIZED_CLIENT))
    }
    bindProvider {
        LecternApi(instance(AUTHORIZED_CLIENT))
    }
    bindProvider {
        TestApi(instance(AUTHORIZED_CLIENT))
    }
    bindProvider {
        DepartmentApi(instance(AUTHORIZED_CLIENT))
    }
}

private fun httpClient(urlServer: String) = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(HttpRequestRetry) {
        retryOnServerErrors(maxRetries = 5)
        exponentialDelay()
    }
    install(HttpCache)
    expectSuccess = true
    defaultRequest {
        url(urlServer)
    }
}