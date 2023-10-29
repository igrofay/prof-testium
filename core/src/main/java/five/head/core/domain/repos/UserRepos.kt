package five.head.core.domain.repos

import five.head.core.domain.model.profile.UserRole

interface UserRepos {
    val refreshToken: String?
    val accessToken: String?
    val userRole: UserRole?

    fun reset()

    fun setTokens(refreshToken: String,accessToken: String)
    fun setRole(role: UserRole)
}