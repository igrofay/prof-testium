package five.head.proftestium.nav.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import five.head.proftestium.nav.model.ManagerContentRouting
import five.head.proftestium.profle_user.view.ProfileUserScreen

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

            }
            composable(ManagerContentRouting.Profile.route) {
                ProfileUserScreen()
            }
        }
    }
}
