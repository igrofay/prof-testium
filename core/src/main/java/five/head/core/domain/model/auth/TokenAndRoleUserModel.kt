package five.head.core.domain.model.auth

import five.head.core.domain.model.profile.UserRole

interface TokenAndRoleUserModel {
    val role: UserRole
    val tokenPair: TokenPairModel
    interface TokenPairModel{
        val accessToken: String
        val refreshToken: String
    }
}