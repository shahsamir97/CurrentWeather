package com.example.currentweather.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.currentweather.ui.navigation.AppNavigation
import com.example.currentweather.ui.theme.CurrentWeatherTheme

@Composable
fun CurrentWeatherApp() {
    val navController = rememberNavController()

    CurrentWeatherTheme {
        AppNavigation(navController)
    }
}