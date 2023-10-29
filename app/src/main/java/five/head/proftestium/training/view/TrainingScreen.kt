package five.head.proftestium.training.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import five.head.proftestium.training.model.TrainingRouting

@Composable
fun TrainingScreen() {
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TrainingRouting.Lectures.route
    ){
        composable(TrainingRouting.Lectures.route){

        }
        composable(TrainingRouting.Test.route){

        }
    }
}