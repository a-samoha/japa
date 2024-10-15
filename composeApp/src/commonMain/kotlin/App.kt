import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import lackner.BatteryManager
import presentation.screen.home.HomeScreen

@Composable
internal fun App(
    batteryManager: BatteryManager,
) {
    MaterialTheme {
        HomeScreen(batteryManager)
    }
}