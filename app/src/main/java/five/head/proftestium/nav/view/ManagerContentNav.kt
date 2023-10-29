package five.head.proftestium.nav.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.nav.model.ManagerContentRouting
import five.head.proftestium.profle_user.view.ProfileUserScreen
import five.head.proftestium.test_analytics.view.TestAnalyticsScreen

@Composable
fun ManagerContentNav() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController,
                ManagerContentRouting.items
            )
        }
    ) { paddingContent ->
        NavHost(
            navController = navController,
            startDestination = ManagerContentRouting.Support.route,
            modifier = Modifier
                .padding(paddingContent)
                .fillMaxSize()
        ) {
            composable(ManagerContentRouting.Support.route) {
                Column {
                    AppBar(label = stringResource(R.string.support))
                }
            }
            composable(ManagerContentRouting.Analytics.route){
                TestAnalyticsScreen()
            }
            composable(ManagerContentRouting.Profile.route) {
                ProfileUserScreen()
            }
        }
    }
}
