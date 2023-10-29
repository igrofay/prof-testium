package five.head.proftestium.nav.view

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import five.head.proftestium.nav.model.EmployeeContentRouting
import five.head.proftestium.nav.model.ManagerContentRouting
import five.head.proftestium.nav.model.OtherContentComingSoonRouting
import five.head.proftestium.start.auth.view.AuthScreen
import five.head.proftestium.nav.model.StartRouting
import five.head.proftestium.start.splash.view.SplashScreen

fun NavGraphBuilder.startGraph(navController: NavController) {
    navigation(
        startDestination = StartRouting.Splash.route,
        route = StartRouting.route
    ) {
        composable(StartRouting.Splash.route) {
            SplashScreen(
                goToAuth = {
                    navController.navigate(StartRouting.Auth.route) {
                        popUpTo(StartRouting.Splash.route) { inclusive = true }
                    }
                },
                goToEmployee = {
                    navController.navigate(EmployeeContentRouting.route) {
                        popUpTo(StartRouting.Splash.route) { inclusive = true }
                    }
                },
                goToManager = {
                    navController.navigate(ManagerContentRouting.route) {
                        popUpTo(StartRouting.Splash.route) { inclusive = true }
                    }
                },
                goToOther = {
                    navController.navigate(OtherContentComingSoonRouting.route) {
                        popUpTo(StartRouting.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(StartRouting.Auth.route) {
            AuthScreen(
                goToEmployee = {
                    navController.navigate(EmployeeContentRouting.route) {
                        popUpTo(StartRouting.Auth.route) { inclusive = true }
                    }
                },
                goToManager = {
                    navController.navigate(ManagerContentRouting.route) {
                        popUpTo(StartRouting.Auth.route) { inclusive = true }
                    }
                },
                goToOther = {
                    navController.navigate(OtherContentComingSoonRouting.route) {
                        popUpTo(StartRouting.Auth.route) { inclusive = true }
                    }
                }
            )
        }
    }
}