package com.temetnosce.japa

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.temetnosce.japa.utils.AndroidScreenLockManager
import lackner.BatteryManager

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidScreenLockManager(this).keepScreenOn(true) // Enable screen stay-on behavior

        setContent {
            App(batteryManager = remember { BatteryManager(applicationContext) })
        }
    }
}
