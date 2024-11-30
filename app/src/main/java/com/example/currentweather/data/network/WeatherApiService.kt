package com.example.currentweather.data.network

import com.example.currentweather.data.model.WeatherInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun fetchCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") unit: String,
    ): WeatherInfoDto
}
