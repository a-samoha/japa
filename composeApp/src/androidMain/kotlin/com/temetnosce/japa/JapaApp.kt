package com.temetnosce.japa

import android.app.Application
import com.temetnosce.japa.di.initKoin
import org.koin.android.ext.koin.androidContext

class JapaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@JapaApp)
        }
    }
}