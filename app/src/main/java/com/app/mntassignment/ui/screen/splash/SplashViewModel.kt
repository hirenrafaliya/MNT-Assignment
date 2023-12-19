package com.app.mntassignment.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    // Just a static 3 Sec delay
    fun load() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        delay(3 * 1000)
        _uiState.update { it.copy(isLoading = false, isCompleted = true) }
    }
}