package com.ortiz.tecsupfit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ortiz.tecsupfit.navigation.Screen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(Screen.Home.route, Screen.Reservations.route, Screen.Routines.route, Screen.Profile.route)
    val showBottomBar = currentRoute in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                        label = { Text("Inicio") },
                        selected = currentRoute == Screen.Home.route,
                        onClick = { navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true } } }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.DateRange, contentDescription = "Reservas") },
                        label = { Text("Reservas") },
                        selected = currentRoute == Screen.Reservations.route,
                        onClick = { navController.navigate(Screen.Reservations.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.FitnessCenter, contentDescription = "Rutinas") },
                        label = { Text("Rutinas") },
                        selected = currentRoute == Screen.Routines.route,
                        onClick = { navController.navigate(Screen.Routines.route) }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") },
                        selected = currentRoute == Screen.Profile.route,
                        onClick = { navController.navigate(Screen.Profile.route) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {
                composable(Screen.Home.route) {
                    HomeScreen(onClassClick = { classId -> navController.navigate(Screen.ClassDetail.createRoute(classId)) })
                }
                composable(
                    route = Screen.ClassDetail.route,
                    arguments = listOf(navArgument("classId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("classId") ?: ""
                    ClassDetailScreen(
                        classId = id,
                        onBackClick = { navController.popBackStack() },
                        onReserveClick = { resId -> navController.navigate(Screen.Confirmation.createRoute(resId)) }
                    )
                }
                composable(
                    route = Screen.Confirmation.route,
                    arguments = listOf(navArgument("classId") { type = NavType.StringType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("classId") ?: ""
                    ConfirmationScreen(
                        classId = id,
                        onViewReservationsClick = {
                            navController.navigate(Screen.Reservations.route) {
                                popUpTo(Screen.Home.route)
                            }
                        }
                    )
                }
                composable(Screen.Reservations.route) { ReservationsScreen() }
                composable(Screen.Routines.route) { RoutinesScreen() }
                composable(Screen.Profile.route) { ProfileScreen() }
            }
        }
    }
}