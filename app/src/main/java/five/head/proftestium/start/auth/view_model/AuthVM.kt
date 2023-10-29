package five.head.proftestium.start.auth.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import five.head.core.domain.model.profile.UserRole
import five.head.core.domain.model.auth.AuthError
import five.head.core.domain.repos.AuthRepos
import five.head.core.domain.repos.UserRepos
import five.head.proftestium.R
import five.head.proftestium.start.auth.model.AuthEvent
import five.head.proftestium.start.auth.model.AuthSideEffect
import five.head.proftestium.start.auth.model.AuthState
import five.head.proftestium.common.vm.AppVM
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

@OptIn(OrbitExperimental::class)
class AuthVM(
    override val di: DI
) : AppVM<AuthState, AuthSideEffect, AuthEvent>(), DIAware {

    private val userRepos by di.instance<UserRepos>()
    private val authRepos by di.instance<AuthRepos>()

    override val container: Container<AuthState, AuthSideEffect> = viewModelScope
        .container(AuthState())

    override fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.InputPassword -> changePassword(event.password)
            is AuthEvent.InputPhone -> changePhoneNumber(event.phone)
            AuthEvent.TryAuth -> authentication()
        }
    }


    private fun changePhoneNumber(phone: String) = blockingIntent {
        if (phone.length <= 10)
            reduce {
                state.copy(phone = phone)
            }
    }

    private fun changePassword(password: String) = blockingIntent {
        reduce {
            state.copy(password = password)
        }
    }

    private fun authentication() = intent {
        val result = runCatching {
            authRepos.signIn(state.copy(phone = "8${state.phone}"))
        }
        if (result.isFailure) {
            onError(result.exceptionOrNull()!!)
            return@intent
        }
        val tokenAndRoleUserModel = result.getOrNull()!!
        userRepos.setRole(tokenAndRoleUserModel.role)
        userRepos.setTokens(
            tokenAndRoleUserModel.tokenPair.refreshToken,
            tokenAndRoleUserModel.tokenPair.accessToken
        )
        when (tokenAndRoleUserModel.role) {
            UserRole.Employee -> postSideEffect(AuthSideEffect.UserIsEmployee)
            UserRole.HrManager -> postSideEffect(AuthSideEffect.UserIsOther)
            UserRole.Manager -> postSideEffect(AuthSideEffect.UserIsManager)
            UserRole.Admin -> postSideEffect(AuthSideEffect.UserIsOther)
        }

    }

    override fun onError(er: Throwable) {
        intent {
            when (er) {
                AuthError.PhoneNotFound ->
                    postSideEffect(AuthSideEffect.Message(R.string.phone_not_found))

                AuthError.IncorrectPassword ->
                    postSideEffect(AuthSideEffect.Message(R.string.wrong_password))

                else -> Log.e("AuthVM", er.message.toString())
            }
        }
    }
}