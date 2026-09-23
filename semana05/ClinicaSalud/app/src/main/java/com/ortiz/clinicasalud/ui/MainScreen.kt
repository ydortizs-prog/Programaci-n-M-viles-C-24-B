package com.ortiz.clinicasalud.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ortiz.clinicasalud.navigation.Screen
import com.ortiz.clinicasalud.ui.theme.PurpleContainer
import com.ortiz.clinicasalud.ui.theme.PurplePrimary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(48.dp),
                            shape = CircleShape,
                            color = PurpleContainer
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "JP",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = PurplePrimary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "Juan Pérez",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Paciente",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))


                val isHomeSelected = currentRoute == Screen.Home.route
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = if (isHomeSelected) Icons.Default.RadioButtonChecked else Icons.Default.RadioButtonUnchecked,
                            contentDescription = null
                        )
                    },
                    label = { Text("Inicio") },
                    selected = isHomeSelected,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )


                val isAppointmentsSelected = currentRoute == Screen.MyAppointments.route
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = if (isAppointmentsSelected) Icons.Default.RadioButtonChecked else Icons.Default.RadioButtonUnchecked,
                            contentDescription = null
                        )
                    },
                    label = { Text("Mis citas") },
                    selected = isAppointmentsSelected,
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.MyAppointments.route)
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Clínica Salud+", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PurplePrimary
                    )
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