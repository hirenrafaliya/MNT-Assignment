package com.app.mntassignment.ui.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.mntassignment.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Preview
@Composable
fun SplashScreen(navController: NavHostController = rememberNavController()) {
    val splashViewModel: SplashViewModel = koinViewModel()
    val uiState by splashViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        splashViewModel.load()
    }

    LaunchedEffect(uiState) {
        uiState.onComplete {
            navController.navigate(Screen.CountDownScreen.route)
        }
    }

}