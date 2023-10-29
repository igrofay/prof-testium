package five.head.core.data.model.auth

import five.head.core.domain.model.auth.SignInModel
import kotlinx.serialization.Serializable

@Serializable
internal data class SignInBody(
    override val phone: String,
    override val password: String
) : SignInModel {
    companion object{
        fun SignInModel.fromModelToSignInBody() = SignInBody(phone, password)
    }
}
