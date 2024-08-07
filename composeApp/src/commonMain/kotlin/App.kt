import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import presentation.screen.home.HomeScreen

@Composable
internal fun App() {
    MaterialTheme {
        HomeScreen()
    }
}