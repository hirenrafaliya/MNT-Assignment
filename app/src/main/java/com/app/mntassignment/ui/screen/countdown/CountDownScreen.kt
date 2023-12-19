package com.app.mntassignment.ui.screen.countdown

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountDownScreen() {
    val context = LocalContext.current
    val countDownViewModel: CountDownViewModel = koinViewModel()
    val uiState by countDownViewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    countDownViewModel.setAppInBackground(true)
                }

                Lifecycle.Event.ON_RESUME -> {
                    countDownViewModel.setAppInBackground(false)
                }

                else -> {

                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    CountDownScreenUi(
        timerSecond = uiState.timerSecond,
        timerMills = uiState.timerMills,
        isRunning = uiState.isRunning,
        isPaused = uiState.isPaused,
        progress = uiState.progress,
        onPause = {
            countDownViewModel.pause()
        },
        onStart = {
            countDownViewModel.start(context)
        },
        onReset = {
            countDownViewModel.cancel()
        }
    )
}

@Composable
private fun CountDownScreenUi(
    timerSecond: String,
    timerMills: String,
    isRunning: Boolean,
    isPaused: Boolean,
    progress: Float,
    onPause: () -> Unit,
    onStart: () -> Unit,
    onReset: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TimerView(progress = progress, timerSecond = timerSecond, timerMills = timerMills)

            Spacer(modifier = Modifier.height(12.dp))

            ActionButtons(isRunning, isPaused, onPause, onStart, onReset)

        }
    }
}

@Composable
private fun TimerView(progress: Float, timerSecond: String, timerMills: String) {
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(120.dp),
            progress = progress,
            strokeWidth = 8.dp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = timerSecond, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = timerMills, style = MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
private fun ActionButtons(
    isRunning: Boolean,
    isPaused: Boolean,
    onPause: () -> Unit,
    onStart: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { if (isRunning) onPause() else onStart() }) {
            Text(text = if (isRunning) "PAUSE" else if (isPaused) "RESUME" else "START")
        }
        Spacer(modifier = Modifier.width(12.dp))
        AnimatedVisibility(isRunning) {
            Button(modifier = Modifier
                .fillMaxWidth(), onClick = { onReset() }) {
                Text(text = "CANCEL")
            }
        }

    }
}

@Composable
private fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Countdown Timer",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}