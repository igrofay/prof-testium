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
import five.head.proftestium.nav.model.EmployeeContentRouting

@Composable
fun EmployeeContentNav() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController,
                EmployeeContentRouting.items
            )
        }
    ) { paddingContent->
        NavHost(
            navController = navController,
            startDestination = EmployeeContentRouting.Training.route,
            modifier = Modifier
                .padding(paddingContent)
                .fillMaxSize()
        ){
            composable(EmployeeContentRouting.Support.route){
                Column {
                    AppBar(label = stringResource(R.string.support))
                }
            }
            composable(EmployeeContentRouting.Training.route){
                Column {
                    AppBar(label = stringResource(R.string.lectures))
                }
            }
            composable(EmployeeContentRouting.Profile.route){
                Column {
                    AppBar(label = stringResource(R.string.profile))
                }
            }
        }
    }
}