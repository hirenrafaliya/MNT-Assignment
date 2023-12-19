package com.app.mntassignment.di

import com.app.mntassignment.ui.screen.countdown.CountDownViewModel
import com.app.mntassignment.ui.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Core app module responsible for providing ViewModels
val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { CountDownViewModel() }
}