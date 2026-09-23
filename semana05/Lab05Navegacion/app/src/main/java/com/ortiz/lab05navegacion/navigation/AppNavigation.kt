package com.ortiz.lab05navegacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ortiz.lab05navegacion.screens.theme.DetailScreen
import com.ortiz.lab05navegacion.screens.theme.HomeScreen
import com.ortiz.lab05navegacion.screens.theme.ListScreen
import com.ortiz.lab05navegacion.screens.theme.LoginScreen
import com.ortiz.lab05navegacion.screens.theme.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.List.route) {
            ListScreen(navController = navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable("detail/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull() ?: 0
            DetailScreen(navController = navController, itemId = itemId)
        }
    }
}