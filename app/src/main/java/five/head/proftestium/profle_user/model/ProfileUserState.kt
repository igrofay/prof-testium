package five.head.proftestium.profle_user.model

import five.head.core.domain.model.profile.ProfileUserModel

data class ProfileUserState(
    val fullName: String = "",
    val phone: String = "",
    val email: String = "",
    val isEdit: Boolean = false
){
    companion object{
        fun ProfileUserModel.fromModeToState() = ProfileUserState(
            fullname ?: "", phone.takeLast(10), email ?: "", false
        )
    }
}