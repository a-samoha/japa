package utils

import platform.UIKit.UIApplication

class IosScreenLockManager : ScreenLockManager {

    override fun keepScreenOn(enable: Boolean) {
        UIApplication.sharedApplication.idleTimerDisabled = enable
    }
}