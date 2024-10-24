import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinContext
import presentation.screen.home.HomeScreen
import presentation.theme.JapaAppTheme

@Composable
internal fun CommonCompose() {
    JapaAppTheme {
        KoinContext {
            // val dbClient = koinInject<DbClient>()
            NavHost(
                navController = rememberNavController(),
                startDestination = "home",
            ) {
                composable(route = "home") { HomeScreen() }
            }
        }
    }
}