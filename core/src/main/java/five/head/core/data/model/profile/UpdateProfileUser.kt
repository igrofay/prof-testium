package five.head.core.data.model.profile

import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateProfileUser(
    val email: String,
    val fullname: String,
)