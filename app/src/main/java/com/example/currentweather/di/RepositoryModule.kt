package com.example.currentweather.di

import com.example.currentweather.data.repository.SharedRepository
import com.example.currentweather.data.repository.SharedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSharedRepository(sharedRepositoryImpl: SharedRepositoryImpl): SharedRepository
}
