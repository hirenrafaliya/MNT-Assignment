package com.app.mntassignment.ui.screen.splash

// Responsible for all UI changes on Splash Screen
data class SplashUiState(
    val isLoading: Boolean = true,
    var isCompleted: Boolean = false
) {

    fun onComplete(callback: () -> Unit) {
        if (isCompleted) callback.invoke()
        isCompleted = false
    }
}