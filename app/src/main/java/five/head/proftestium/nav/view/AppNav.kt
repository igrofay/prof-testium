package five.head.proftestium.nav.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import five.head.proftestium.R
import five.head.proftestium.common.ui.appbar.AppBar
import five.head.proftestium.nav.model.EmployeeContentRouting
import five.head.proftestium.nav.model.ManagerContentRouting
import five.head.proftestium.nav.model.OtherContentComingSoonRouting
import five.head.proftestium.nav.model.StartRouting
import five.head.proftestium.training.model.TrainingRouting
import five.head.proftestium.training.view.TestScreen


@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StartRouting.route,
    ){
        startGraph(navController)
        composable(EmployeeContentRouting.route){
            EmployeeContentNav(navController)
        }
        composable(ManagerContentRouting.route){
            ManagerContentNav()
        }
        composable(OtherContentComingSoonRouting.route){
            Column {
                AppBar(label = stringResource(R.string.coming_soon))
            }
        }
        composable(
            TrainingRouting.Test.allRoute(),
            arguments = listOf(navArgument(TrainingRouting.Test.idTest) {
                type = NavType.StringType
            }),
        ){
            TestScreen{
                navController.popBackStack()
            }
        }
    }
}