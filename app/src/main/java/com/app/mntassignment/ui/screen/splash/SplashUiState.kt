package com.app.mntassignment.ui.screen.splash

data class SplashUiState(
    val isLoading: Boolean = true,
    var isCompleted: Boolean = false
) {
    fun onComplete(callback: () -> Unit) {
        if (isCompleted) callback.invoke()
        isCompleted = false
    }
}