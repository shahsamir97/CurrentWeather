package com.example.currentweather.data.model

data class WeatherInfoDto(
    val base: String?,
    val cod: Int?,
    val dt: Int?,
    val id: Int?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<Weather>?,
)
