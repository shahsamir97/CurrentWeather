package com.example.currentweather.di

import com.example.currentweather.utils.JsonFileManager
import com.example.currentweather.utils.JsonFileManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class JsonUtilsModule {

    @Binds
    abstract fun bindJsonUtils(jsonFileUtilsImpl: JsonFileManagerImpl): JsonFileManager
}
