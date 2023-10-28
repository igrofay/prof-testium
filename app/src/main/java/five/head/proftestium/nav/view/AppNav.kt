package five.head.proftestium.nav.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import five.head.proftestium.nav.model.StartRouting


@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StartRouting.Splash.route,
    ){

    }
}