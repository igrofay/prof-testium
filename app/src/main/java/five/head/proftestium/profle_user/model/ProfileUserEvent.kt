package five.head.proftestium.profle_user.model

sealed class ProfileUserEvent {
    data class InputFullName(val name: String) : ProfileUserEvent()
    data class InputEmail(val email: String) : ProfileUserEvent()
    data object Save : ProfileUserEvent()
}