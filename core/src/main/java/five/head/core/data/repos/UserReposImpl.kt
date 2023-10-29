package five.head.core.data.repos

import android.content.SharedPreferences
import androidx.core.content.edit
import five.head.core.domain.model.profile.UserRole
import five.head.core.domain.repos.UserRepos

internal class UserReposImpl(
    private val sharedPreferences: SharedPreferences,
) : UserRepos {
    private val keyRefreshToken = "keyRefreshToken"
    private val keyAccessToken = "keyAccessToken"
    private val keyUserRole = "keyUserRole"
    override val refreshToken: String?
        get() = sharedPreferences.getString(keyRefreshToken, null)
    override val accessToken: String?
        get() = sharedPreferences.getString(keyAccessToken, null)
    override val userRole: UserRole?
        get() = sharedPreferences.getString(keyUserRole, null)?.let { UserRole.valueOf(it) }

    override fun reset() = sharedPreferences.edit {
        putString(keyRefreshToken, null)
        putString(keyAccessToken, null)

    }


    override fun setTokens(
        refreshToken: String,
        accessToken: String
    ) = sharedPreferences.edit {
        putString(keyRefreshToken, refreshToken)
        putString(keyAccessToken, accessToken)
    }

    override fun setRole(role: UserRole) = sharedPreferences.edit {
        putString(keyUserRole, role.name)
    }
}