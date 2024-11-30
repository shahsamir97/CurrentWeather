package com.example.currentweather.data.repository

import com.example.currentweather.data.model.WeatherInfoDto
import com.example.currentweather.data.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface HomeRepository {
    suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): WeatherInfoDto
}

class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): HomeRepository {

    override suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): WeatherInfoDto {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchCurrentWeather(latitude, longitude)
        }
    }
}
