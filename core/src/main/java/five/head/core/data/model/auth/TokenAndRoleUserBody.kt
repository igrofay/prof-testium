package five.head.core.data.model.auth

import five.head.core.domain.model.auth.TokenAndRoleUserModel
import five.head.core.domain.model.profile.UserRole
import kotlinx.serialization.Serializable

@Serializable
internal data class TokenAndRoleUserBody(
    override val role: UserRole,
    override val tokenPair: TokenPairBody
): TokenAndRoleUserModel {
    @Serializable
    data class TokenPairBody(
        override val accessToken: String,
        override val refreshToken: String
    ): TokenAndRoleUserModel.TokenPairModel
}
