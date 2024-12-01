package com.example.currentweather.data.source

import com.example.currentweather.R
import com.example.currentweather.data.model.Zila
import com.example.currentweather.utils.JsonFileManager
import com.google.gson.reflect.TypeToken
import okio.IOException
import javax.inject.Inject

interface LocalDataSource {
    suspend fun fetchZilaList(): List<Zila>
}

class LocalDataSourceImpl @Inject constructor(
    private val jsonFileManager: JsonFileManager,
): LocalDataSource {

    override suspend fun fetchZilaList(): List<Zila> {
        return try {
            val zilaListType = object : TypeToken<List<Zila>>() {}.type
            val cities: List<Zila> = jsonFileManager.readJson(R.raw.zila_list, zilaListType)

            cities
        } catch (e: IOException) {
            throw LocalDataException(message = e.message.toString(), e)
        }
    }
}

class LocalDataException(message: String, cause: Throwable): Exception(message, cause)
