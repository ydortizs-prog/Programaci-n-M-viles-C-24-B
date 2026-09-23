package com.ortiz.tecsupfit.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ClassDetail : Screen("class_detail/{classId}") {
        fun createRoute(classId: String) = "class_detail/$classId"
    }
    object Confirmation : Screen("confirmation/{classId}") {
        fun createRoute(classId: String) = "class_detail/$classId/confirm"
    }
    object Reservations : Screen("reservations")
    object Routines : Screen("routines")
    object Profile : Screen("profile")
}