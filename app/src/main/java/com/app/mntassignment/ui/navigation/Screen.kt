package com.app.mntassignment.ui.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object CountDownScreen: Screen("countdown_screen")
}