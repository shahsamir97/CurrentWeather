package com.example.currentweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currentweather.ui.SharedViewModel
import com.example.currentweather.ui.home.HomeScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route
    ) {
        composable(AppScreens.Home.route) {
            HomeScreen()
        }
    }
}