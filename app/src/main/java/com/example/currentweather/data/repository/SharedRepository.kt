package com.example.currentweather.data.repository

import com.example.currentweather.data.model.WeatherInfoDto
import com.example.currentweather.data.model.Zila
import com.example.currentweather.data.source.LocalDataException
import com.example.currentweather.data.source.LocalDataSource
import com.example.currentweather.data.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SharedRepository {
    suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): WeatherInfoDto
    suspend fun fetchZilaList(): List<Zila>
}

class SharedRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): SharedRepository {

    override suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): WeatherInfoDto {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchCurrentWeather(latitude, longitude)
        }
    }

    override suspend fun fetchZilaList(): List<Zila> {
        return try {
            localDataSource.fetchZilaList()
        } catch (e: LocalDataException) {
            emptyList()
        }
    }
}
