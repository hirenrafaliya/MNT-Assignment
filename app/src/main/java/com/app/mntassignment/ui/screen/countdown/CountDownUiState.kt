package com.app.mntassignment.ui.screen.countdown

/** Responsible for all UI changes on Countdown screen
 * @param timerSecond Seconds left to finish the timer
 * @param timerMills Milliseconds to display
 * @param timer Milliseconds remaining to finish the timer
 * @param progress Overall progress of the timer
 * @param isRunning Status of the timer
 * @param isPaused Status of the timer
 * @param isAppInBackground To check if the App is in background/foreground
 */
data class CountDownUiState (
    val timerSecond: String = "",
    val timerMills: String = "",
    val timer: Long = 60 * 1000,
    val progress: Float = timer.toFloat(),
    val isRunning: Boolean = false,
    val isPaused: Boolean = false,
    val isAppInBackground: Boolean = false
)