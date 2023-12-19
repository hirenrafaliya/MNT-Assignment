package com.app.mntassignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.mntassignment.ui.screen.countdown.CountDownScreen
import com.app.mntassignment.ui.screen.splash.SplashScreen

// Controls the overall navigation of the app
@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.CountDownScreen.route) {
            CountDownScreen()
        }
    }
}