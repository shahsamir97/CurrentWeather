package com.example.currentweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currentweather.ui.SharedViewModel
import com.example.currentweather.ui.screens.home.HomeEvents
import com.example.currentweather.ui.screens.home.HomeScreen
import com.example.currentweather.ui.screens.home.HomeState

@Composable
fun AppNavigation(navController: NavHostController) {
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route
    ) {
        composable(AppScreens.Home.route) {
            val state by sharedViewModel.homeState.collectAsStateWithLifecycle()

            HomeScreen(state = state, events = sharedViewModel)
        }
    }
}