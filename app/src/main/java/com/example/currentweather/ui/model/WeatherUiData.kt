package com.example.currentweather.ui.model

data class WeatherUiData(
    val cityName: String,
    val mainTemp: String,
    val tempFeelsLike: String,
    val minTemp: String,
    val maxTemp: String,
    val iconUrl: String,
    val description: String,
)
