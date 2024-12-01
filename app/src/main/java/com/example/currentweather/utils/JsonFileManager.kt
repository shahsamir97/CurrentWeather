package com.example.currentweather.utils

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

interface JsonFileManager {
    fun <T> readJson(resourceId: Int, type: Type): T
}

class JsonFileManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): JsonFileManager {

    override fun <T> readJson(resourceId: Int, type: Type): T {
        val jsonFile = context.resources.openRawResource(resourceId)
            .bufferedReader().use { it.readText() }

        return Gson().fromJson(jsonFile, type)
    }
}
