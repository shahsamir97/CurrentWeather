package com.example.currentweather.di

import com.example.currentweather.data.source.RemoteDataSource
import com.example.currentweather.data.source.RemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RemoteSourceModule {

    @Binds
    abstract fun bindRemoteSource(remoteSourceImpl: RemoteSourceImpl): RemoteDataSource
}
