package five.head.core.domain.repos

import five.head.core.domain.model.profile.ProfileUserModel

interface ProfileUserRepos {
    suspend fun getProfile() : ProfileUserModel
    suspend fun updateProfile(fullName: String, email: String): ProfileUserModel
}