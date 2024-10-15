import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import lackner.BatteryManager

fun MainViewController() = ComposeUIViewController {
    App(
        batteryManager = remember { BatteryManager() }
    )
}