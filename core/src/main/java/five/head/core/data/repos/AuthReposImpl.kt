package five.head.core.data.repos

import five.head.core.data.data_source.network.AuthApi
import five.head.core.data.model.auth.SignInBody.Companion.fromModelToSignInBody
import five.head.core.data.model.auth.TokenAndRoleUserBody
import five.head.core.domain.model.auth.AuthError
import five.head.core.domain.model.auth.SignInModel
import five.head.core.domain.model.auth.TokenAndRoleUserModel
import five.head.core.domain.repos.AuthRepos
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode

internal class AuthReposImpl(
    private val authApi: AuthApi
) : AuthRepos {
    override suspend fun signIn(signInModel: SignInModel): TokenAndRoleUserModel {
        try {
            return authApi.signIn(signInModel.fromModelToSignInBody()).body<TokenAndRoleUserBody>()
        }catch (e: ClientRequestException){
            throw when(e.response.status){
                HttpStatusCode.BadRequest-> AuthError.IncorrectPassword
                HttpStatusCode.NotFound -> AuthError.PhoneNotFound
                else -> e
            }
        }
    }

}