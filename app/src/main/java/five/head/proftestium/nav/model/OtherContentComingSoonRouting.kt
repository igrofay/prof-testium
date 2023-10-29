package five.head.proftestium.nav.model

import five.head.proftestium.common.model.AppRouting

sealed class OtherContentComingSoonRouting(route:String) : AppRouting(route){
    companion object{
        const val route = "other_content_coming_soon_routing"
    }
}