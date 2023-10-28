package five.head.proftestium.nav.model


sealed class StartRouting(val route: String) {
    data object Splash : StartRouting("${route}_splash")
    data object Auth : StartRouting("${route}_auth")
    companion object {
        const val route = "start_routing"
    }
}
