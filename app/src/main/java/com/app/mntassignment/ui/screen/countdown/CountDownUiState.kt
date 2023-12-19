package com.app.mntassignment.ui.screen.countdown

data class CountDownUiState (
    val timerSecond: String = "",
    val timerMills: String = "",
    val timer: Long = 60 * 1000,
    val progress: Float = timer.toFloat(),
    val isRunning: Boolean = false,
    val isPaused: Boolean = false,
    val isAppInBackground: Boolean = false
)