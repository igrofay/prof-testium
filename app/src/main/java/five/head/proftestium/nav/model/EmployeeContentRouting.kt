package five.head.proftestium.nav.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import five.head.proftestium.R

sealed class EmployeeContentRouting(
    override val route: String,
    @StringRes override val label: Int,
    @DrawableRes override val icon: Int
) : BottomNavItem {
    data object Support :
        EmployeeContentRouting("${route}_support", R.string.support, R.drawable.chat)

    data object Training :
        EmployeeContentRouting("${route}_training", R.string.training, R.drawable.activity)

    data object Profile :
        EmployeeContentRouting("${route}_profile", R.string.profile, R.drawable.profile)

    companion object {
        val route = "employee_content_routing"
        val items by lazy { listOf<BottomNavItem>(Support, Training, Profile) }
    }
}