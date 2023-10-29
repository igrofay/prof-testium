package five.head.proftestium.profle_user.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import five.head.core.domain.repos.ProfileUserRepos
import five.head.proftestium.common.vm.AppVM
import five.head.proftestium.profle_user.model.ProfileUserEvent
import five.head.proftestium.profle_user.model.ProfileUserSideEffect
import five.head.proftestium.profle_user.model.ProfileUserState
import five.head.proftestium.profle_user.model.ProfileUserState.Companion.fromModeToState
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class ProfileUserVM(
    override val di: DI
) : AppVM<ProfileUserState, ProfileUserSideEffect,ProfileUserEvent>(), DIAware {
    private val profileUserRepos by di.instance<ProfileUserRepos>()

    override val container: Container<ProfileUserState, ProfileUserSideEffect> =
        viewModelScope.container(ProfileUserState()){
            loadProfileUser()
        }

    override fun onEvent(event: ProfileUserEvent) {
        when(event){
            is ProfileUserEvent.InputEmail -> changEmail(event.email)
            is ProfileUserEvent.InputFullName -> changFullName(event.name)
            ProfileUserEvent.Save -> saveChanges()
        }
    }
    private fun loadProfileUser() = intent {
        runCatching { profileUserRepos.getProfile() }
            .onSuccess {  model ->
                reduce { model.fromModeToState() }
            }
            .onFailure(::onError)
    }
    private fun changFullName(name: String) = intent {
        reduce {
            state.copy(fullName = name, isEdit = true)
        }
    }


    private fun changEmail(email: String) = intent {
        reduce {
            state.copy(
                email = email, isEdit = true
            )
        }
    }
    private fun saveChanges() = intent {
        runCatching { profileUserRepos.updateProfile(state.fullName, state.email) }
            .onSuccess { model ->
                reduce { model.fromModeToState() }
            }
            .onFailure(::onError)
    }

    override fun onError(er: Throwable) {
        Log.e("ProfileUserVM", er.message.toString())
    }
}