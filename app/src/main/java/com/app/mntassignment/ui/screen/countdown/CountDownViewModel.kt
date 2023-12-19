package com.app.mntassignment.ui.screen.countdown

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountDownViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(CountDownUiState())
    val uiState = _uiState.asStateFlow()

    private lateinit var timer: CountDownTimer

    fun start() = viewModelScope.launch {
        _uiState.update { it.copy(isRunning = true, isPaused = false) }
        timer = object : CountDownTimer(uiState.value.timer, 1) {
            override fun onTick(mills: Long) {
                _uiState.update {
                    it.copy(
                        timerSecond = calculateSeconds(mills).toString(),
                        timerMills = calculateMills(mills),
                        timer = mills,
                        progress = (mills / 60000f).toFloat()
                    )
                }
            }

            override fun onFinish() {
                _uiState.update { it.copy(isRunning = false, timer = 60 * 1000) }
            }

        }
        timer.start()
    }

    fun pause() = viewModelScope.launch {
        _uiState.update { it.copy(isRunning = false, isPaused = true) }
        timer.cancel()
    }

    fun cancel() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isRunning = false,
                timer = 60 * 1000,
                timerSecond = "00",
                timerMills = "000",
                progress = 0.0f
            )
        }
        timer.cancel()
    }

    private fun calculateSeconds(mills: Long) = String.format("%02d", mills / 1000).toInt()
    private fun calculateMills(mills: Long) = String.format("%03d", (mills % 1000).toInt())
}