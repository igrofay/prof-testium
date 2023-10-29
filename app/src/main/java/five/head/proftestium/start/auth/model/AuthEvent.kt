package five.head.proftestium.start.auth.model

sealed class AuthEvent{
    class InputPhone(val phone:String): AuthEvent()
    class InputPassword(val password: String) : AuthEvent()
    data object TryAuth : AuthEvent()
}
