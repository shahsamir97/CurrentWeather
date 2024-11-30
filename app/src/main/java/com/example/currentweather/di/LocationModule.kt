package com.example.currentweather.di

import com.example.currentweather.framework.LocationHelper
import com.example.currentweather.framework.LocationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    abstract fun bindLocationHelper(locationHelperImpl: LocationHelperImpl): LocationHelper
}