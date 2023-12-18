package com.app.mntassignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.mntassignment.ui.screen.countdown.CountDownScreen
import com.app.mntassignment.ui.screen.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen()
        }
        composable(Screen.CountDownScreen.route) {
            CountDownScreen()
        }
    }
}