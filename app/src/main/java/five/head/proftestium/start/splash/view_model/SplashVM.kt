package five.head.proftestium.start.splash.view_model

import androidx.lifecycle.viewModelScope
import five.head.core.domain.model.profile.UserRole
import five.head.core.domain.repos.UserRepos
import five.head.proftestium.common.vm.AppVM
import five.head.proftestium.start.splash.model.SplashEvent
import five.head.proftestium.start.splash.model.SplashSideEffect
import five.head.proftestium.start.splash.model.SplashState
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class SplashVM(
    override val di: DI
) : AppVM<SplashState, SplashSideEffect, SplashEvent>(), DIAware{
    private val userRepos by di.instance<UserRepos>()

    override val container: Container<SplashState, SplashSideEffect> = viewModelScope
        .container(SplashState){
            initUserRole()
        }

    override fun onEvent(event: SplashEvent) {}

    override fun onError(er: Throwable) {
    }
    private fun initUserRole() = intent {
        when(userRepos.userRole){
            UserRole.Employee -> postSideEffect(SplashSideEffect.UserIsEmployee)
            UserRole.HrManager -> postSideEffect(SplashSideEffect.UserIsOther)
            UserRole.Manager -> postSideEffect(SplashSideEffect.UserIsManager)
            UserRole.Admin -> postSideEffect(SplashSideEffect.UserIsOther)
            null -> postSideEffect(SplashSideEffect.NeedAuth)
        }
    }
}