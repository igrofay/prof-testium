package five.head.core.domain.repos

import five.head.core.domain.model.auth.SignInModel
import five.head.core.domain.model.auth.TokenAndRoleUserModel

interface AuthRepos {

    suspend fun signIn(signInModel: SignInModel) : TokenAndRoleUserModel

}