package com.ortiz.clinicasalud.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DoctorDetail : Screen("doctor_detail/{doctorId}") {
        fun createRoute(doctorId: String) = "doctor_detail/$doctorId"
    }
    object ScheduleAppointment : Screen("schedule/{doctorId}") {
        fun createRoute(doctorId: String) = "schedule/$doctorId"
    }
    object Confirmation : Screen("confirmation/{doctorId}/{date}/{time}") {
        fun createRoute(doctorId: String, date: String, time: String) =
            "confirmation/$doctorId/$date/$time"
    }
    object MyAppointments : Screen("my_appointments")
    object History : Screen("history")
    object Profile : Screen("profile")
}