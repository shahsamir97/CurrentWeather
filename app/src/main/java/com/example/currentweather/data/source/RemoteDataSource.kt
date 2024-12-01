package com.example.currentweather.data.source

import com.example.currentweather.data.model.WeatherInfoDto
import com.example.currentweather.data.network.WeatherApiService
import com.example.currentweather.utils.UNITS_KEY
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): WeatherInfoDto
}

class RemoteDataSourceImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
): RemoteDataSource {

    override suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): WeatherInfoDto =
        weatherApiService.fetchCurrentWeather(
            latitude = latitude,
            longitude = longitude,
            unit = UNITS_KEY,
        )
}
