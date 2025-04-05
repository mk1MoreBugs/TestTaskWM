package com.example.pix.ui.navigation

import androidx.navigation.NavHostController

class PixNavigationActions(private val navController: NavHostController) {
    fun navigateTo(route: Destinations) {
        navController.navigate(route = route) {
            launchSingleTop = true
        }
    }
}