package com.ortiz.clinicasalud.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ortiz.clinicasalud.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Clínica Salud+",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    label = { Text("Mis citas") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.MyAppointments.route)
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Clínica Salud+") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onDoctorClick = { doctorId: String ->
                                navController.navigate(Screen.DoctorDetail.createRoute(doctorId))
                            }
                        )
                    }

                    composable(
                        route = Screen.DoctorDetail.route,
                        arguments = listOf(navArgument("doctorId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val doctorId = backStackEntry.arguments?.getString("doctorId") ?: ""
                        DoctorDetailScreen(
                            doctorId = doctorId,
                            onBackClick = { navController.popBackStack() },
                            onScheduleClick = { id: String ->
                                navController.navigate(Screen.ScheduleAppointment.createRoute(id))
                            }
                        )
                    }

                    composable(
                        route = Screen.ScheduleAppointment.route,
                        arguments = listOf(navArgument("doctorId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val doctorId = backStackEntry.arguments?.getString("doctorId") ?: ""
                        ScheduleAppointmentScreen(
                            doctorId = doctorId,
                            onBackClick = { navController.popBackStack() },
                            onConfirmClick = { id: String, date: String, time: String ->
                                navController.navigate(Screen.Confirmation.createRoute(id, date, time))
                            }
                        )
                    }

                    composable(
                        route = Screen.Confirmation.route,
                        arguments = listOf(
                            navArgument("doctorId") { type = NavType.StringType },
                            navArgument("date") { type = NavType.StringType },
                            navArgument("time") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val doctorId = backStackEntry.arguments?.getString("doctorId") ?: ""
                        val date = backStackEntry.arguments?.getString("date") ?: ""
                        val time = backStackEntry.arguments?.getString("time") ?: ""

                        ConfirmationScreen(
                            doctorId = doctorId,
                            date = date,
                            time = time,
                            onViewAppointmentsClick = {
                                navController.navigate(Screen.MyAppointments.route) {
                                    popUpTo(Screen.Home.route)
                                }
                            }
                        )
                    }

                    composable(Screen.MyAppointments.route) {
                        MyAppointmentsScreen()
                    }
                }
            }
        }
    }
}