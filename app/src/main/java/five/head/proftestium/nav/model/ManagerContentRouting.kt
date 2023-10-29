package five.head.proftestium.nav.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import five.head.proftestium.R
import five.head.proftestium.common.model.AppRouting

sealed class ManagerContentRouting(
    route: String,
    @StringRes override val label: Int,
    @DrawableRes override val icon: Int
) : AppRouting(route), BottomNavItem {
    data object Support : ManagerContentRouting(
        "${route}_support",
        R.string.support,
        R.drawable.chat,
    )
    data object Profile : ManagerContentRouting(
        "${route}_profile",
        R.string.profile,
        R.drawable.profile
    )
    companion object{
        const val route = "manager_content_routing"
        val items by lazy { listOf<BottomNavItem>(Support, Profile) }
    }
}