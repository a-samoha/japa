package com.temetnosce.japa

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.temetnosce.japa.presentation.screen.emend.EmendScreen
import com.temetnosce.japa.presentation.screen.home.HomeScreen
import com.temetnosce.japa.presentation.screen.settings.SettingsScreen

val LocalNavController = compositionLocalOf<NavController> { error("No NavController provided") }

// region ROUTES
private const val SIGNUP = "signup"
private const val LOGIN = "login"
private const val HOME = "home"
private const val SETTINGS = "settings"
private const val EMEND = "emend"
// endregion

// region ARGUMENTS
private const val StartTimestamp = "startTimestamp"
// endregion

// region auxiliary functions
fun NavController.navigateToEmendScreen(startTimestamp: Long) {
    this.navigate("$EMEND/$startTimestamp")
}

fun NavController.navigateToSettingsScreen() {
    this.navigate(SETTINGS)
}
// endregion

@Composable
internal fun Navigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = HOME,
        ) {
            composable(route = HOME) { HomeScreen() }

            composable(route = SETTINGS) { SettingsScreen() }

            composable(
                route = "$EMEND/{$StartTimestamp}",
                arguments = listOf(navArgument(StartTimestamp) {
                    type = NavType.LongType
                })
            ) { backStackEntry ->
                val startTimestamp =
                    backStackEntry.arguments?.getLong(StartTimestamp) ?: 0L
                EmendScreen(startTimestamp = startTimestamp)
            }
        }
    }
}
