package five.head.core.data.model.profile

import five.head.core.domain.model.profile.ProfileUserModel
import kotlinx.serialization.Serializable

@Serializable
internal data class ProfileUserBody(
    override val phone: String,
    override val email: String?,
    override val fullname: String?
): ProfileUserModel
