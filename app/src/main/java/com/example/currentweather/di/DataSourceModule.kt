package com.example.currentweather.di

import com.example.currentweather.data.source.LocalDataSource
import com.example.currentweather.data.source.LocalDataSourceImpl
import com.example.currentweather.data.source.RemoteDataSource
import com.example.currentweather.data.source.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}
