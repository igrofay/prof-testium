package five.head.core.domain.model.auth

sealed class AuthError : Error(){
    data object IncorrectPassword : AuthError()
    data object PhoneNotFound : AuthError()
}