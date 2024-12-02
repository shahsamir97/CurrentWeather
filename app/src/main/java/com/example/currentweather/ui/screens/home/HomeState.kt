package com.example.currentweather.ui.screens.home

import com.example.currentweather.ui.model.WeatherUiData

data class HomeState(
    val weatherData: WeatherUiData,
    val showLocationPermissionDialog: Boolean,
    val showAlert: Boolean,
) {
    companion object {
        val default = HomeState(
            showLocationPermissionDialog = false,
            showAlert = false,
            weatherData = WeatherUiData(
                cityName = "",
                mainTemp = "",
                minTemp = "",
                maxTemp = "",
                tempFeelsLike = "",
                description = "",
                iconUrl = "",
            )
        )
    }
}
