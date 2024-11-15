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
import com.temetnosce.japa.presentation.theme.JapaAppTheme
import org.koin.compose.KoinContext

val LocalNavController = compositionLocalOf<NavController> { error("No NavController provided") }

fun NavController.navigateToEmendScreen(startTimestamp: Long) {
    this.navigate("emend/$startTimestamp")
}

@Composable
internal fun CommonCompose() {
    JapaAppTheme {
        KoinContext {
            val navController = rememberNavController()
            CompositionLocalProvider(LocalNavController provides navController) {
                NavHost(
                    navController = navController,
                    startDestination = "home",
                ) {
                    composable(route = "home") { HomeScreen() }

                    composable(
                        route = "emend/{startTimestamp}",
                        arguments = listOf(navArgument("startTimestamp") {
                            type = NavType.LongType
                        })
                    ) { backStackEntry ->
                        val startTimestamp =
                            backStackEntry.arguments?.getLong("startTimestamp") ?: 0L
                        EmendScreen(startTimestamp = startTimestamp)
                    }
                }
            }
        }
    }
}