package five.head.proftestium.nav.view

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import five.head.proftestium.auth.view.AuthScreen
import five.head.proftestium.nav.model.StartRouting
import five.head.proftestium.splash.view.SplashScreen

fun NavGraphBuilder.startGraph(navController: NavController){
    navigation(
        startDestination = StartRouting.Splash.route,
        route = StartRouting.route
    ) {
        composable(StartRouting.Splash.route){
            SplashScreen()
        }
        composable(StartRouting.Auth.route){
            AuthScreen()
        }
    }
}