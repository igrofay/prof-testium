package five.head.core.data.repos

import five.head.core.data.data_source.network.ProfileUserApi
import five.head.core.data.model.profile.UpdateProfileUser
import five.head.core.data.model.profile.ProfileUserBody
import five.head.core.domain.model.profile.ProfileUserModel
import five.head.core.domain.repos.ProfileUserRepos
import io.ktor.client.call.body

internal class ProfileUserReposImpl(
    private val profileUserApi: ProfileUserApi,
) : ProfileUserRepos{

    override suspend fun getProfile(): ProfileUserModel =
        profileUserApi.getProfile().body<ProfileUserBody>()

    override suspend fun updateProfile(fullName: String, email: String): ProfileUserModel =
        profileUserApi.updateProfile(UpdateProfileUser(email, fullName)).body<ProfileUserBody>()

}