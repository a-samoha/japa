package com.temetnosce.japa

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@App)
        }
    }
}