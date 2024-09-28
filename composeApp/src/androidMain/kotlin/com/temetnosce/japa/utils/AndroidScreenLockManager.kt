package com.temetnosce.japa.utils

import android.app.Activity
import android.view.WindowManager
import utils.ScreenLockManager

class AndroidScreenLockManager(private val activity: Activity) : ScreenLockManager {

    override fun keepScreenOn(enable: Boolean) {
        if (enable) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        } else {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }
}