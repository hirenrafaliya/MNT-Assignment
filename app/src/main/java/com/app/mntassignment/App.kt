package com.app.mntassignment

import android.app.Application
import com.app.mntassignment.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        // Preload Koin modules to provide the required dependencies
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}