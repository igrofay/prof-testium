package five.head.proftestium.start.splash.model

sealed class SplashSideEffect {
    data object NeedAuth : SplashSideEffect()
    data object UserIsEmployee : SplashSideEffect()
    data object UserIsManager : SplashSideEffect()
    data object UserIsOther: SplashSideEffect()
}