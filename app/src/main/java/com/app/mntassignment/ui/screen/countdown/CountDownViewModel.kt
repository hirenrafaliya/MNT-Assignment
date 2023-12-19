package com.app.mntassignment.ui.screen.countdown

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.mntassignment.util.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountDownViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(CountDownUiState())
    val uiState = _uiState.asStateFlow()

    private lateinit var timer: CountDownTimer

    fun start(context: Context) = viewModelScope.launch {
        _uiState.update { it.copy(isRunning = true, isPaused = false) }
        timer = object : CountDownTimer(uiState.value.timer, 1) {
            override fun onTick(mills: Long) {
                _uiState.update {
                    it.copy(
                        timerSecond = calculateSeconds(mills).toString(),
                        timerMills = calculateMills(mills),
                        timer = mills,
                        progress = (mills / 60000f)
                    )
                }
            }

            override fun onFinish() {
                _uiState.update {
                    it.copy(
                        isRunning = false,
                        timer = 60 * 1000,
                        timerSecond = "00",
                        timerMills = "000"
                    )
                }
                if (_uiState.value.isAppInBackground)
                    NotificationHelper.showNotification(
                        context,
                        "Countdown completed",
                        ""
                    )
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

    fun setAppInBackground(isAppInBackground: Boolean) = _uiState.update { it.copy(isAppInBackground = isAppInBackground)}

    private fun calculateSeconds(mills: Long) = String.format("%02d", mills / 1000).toInt()
    private fun calculateMills(mills: Long) = String.format("%03d", (mills % 1000).toInt())
}