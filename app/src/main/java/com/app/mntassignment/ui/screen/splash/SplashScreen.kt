package com.app.mntassignment.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    SplashScreenUi()
}

@Composable
private fun SplashScreenUi() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Countdown Timer", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Assignment", style = MaterialTheme.typography.bodySmall)
    }
}