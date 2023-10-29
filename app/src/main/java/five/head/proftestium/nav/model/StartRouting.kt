package five.head.proftestium.nav.model

import five.head.proftestium.common.model.AppRouting


sealed class StartRouting(route: String) : AppRouting(route){
    data object Splash : StartRouting("${route}_splash")
    data object Auth : StartRouting("${route}_auth")
    companion object {
        const val route = "start_routing"
    }
}
