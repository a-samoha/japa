import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import lackner.BatteryManager

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Japa",
    ) {
        App(batteryManager = remember { BatteryManager() })
    }
}