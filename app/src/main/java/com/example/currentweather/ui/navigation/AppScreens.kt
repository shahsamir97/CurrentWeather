package com.example.currentweather.ui.navigation

sealed class AppScreens(val route: String): NavigationRoute {
    object Home : AppScreens("home")
    object Search : AppScreens("search")
}