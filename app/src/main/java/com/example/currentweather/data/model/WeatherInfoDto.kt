package com.example.currentweather.data.model

import com.example.currentweather.ui.model.WeatherUiData
import com.example.currentweather.utils.createIconUrl

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

fun WeatherInfoDto.toWeatherUiData() =
    WeatherUiData(
        cityName = name ?: "",
        mainTemp = main?.temp?.toInt()?.toString() ?: "",
        minTemp = main?.temp_min?.toInt()?.toString() ?: "",
        maxTemp = main?.temp_max?.toInt()?.toString() ?: "",
        tempFeelsLike = main?.feels_like?.toInt()?.toString() ?: "",
        description = weather?.get(0)?.main ?: "",
        iconUrl = weather?.get(0)?.icon?.let { createIconUrl(it) } ?: ""
    )
