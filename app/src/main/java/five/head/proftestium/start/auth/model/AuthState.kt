package five.head.proftestium.start.auth.model

import five.head.core.domain.model.auth.SignInModel

data class AuthState(
    override val phone: String = "",
    override val password: String = "",
) : SignInModel