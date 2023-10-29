package five.head.proftestium.start.auth.model

import androidx.annotation.StringRes


sealed class AuthSideEffect {
    data class Message(@StringRes val text: Int): AuthSideEffect()
    data object UserIsEmployee : AuthSideEffect()
    data object UserIsManager : AuthSideEffect()
    data object UserIsOther: AuthSideEffect()
}