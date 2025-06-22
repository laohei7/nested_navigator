package com.laohei.nested_navigator.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.laohei.nested_navigator.presentation.main.MainGraph
import com.laohei.nested_navigator.presentation.settings.SettingsScreen
import com.laohei.nested_navigator.presentation.splash.SplashScreen

@Composable
fun App() {
    val globalNavController = rememberNavController()

    NavHost(
        navController = globalNavController,
        startDestination = Route.Splash
    ) {
        composable<Route.Splash> {
            SplashScreen {
                globalNavController.navigate(it) {
                    // 移除 SplashScreen
                    popUpTo<Route.Splash> {
                        inclusive = true
                    }
                    // 确保MainGraph是单例，避免重复创建
                    launchSingleTop = true
                }
            }
        }
        composable<Route.MainGraph> {
            MainGraph {
                globalNavController.navigate(it)
            }
        }
        composable<Route.Settings> { SettingsScreen { globalNavController.navigateUp() } }
    }
}