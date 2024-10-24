import platform.UIKit.UIApplication
import utils.ScreenLockManager

class IosScreenLockManager : ScreenLockManager {

    override fun keepScreenOn(enable: Boolean) {
        UIApplication.sharedApplication.idleTimerDisabled = enable
    }
}