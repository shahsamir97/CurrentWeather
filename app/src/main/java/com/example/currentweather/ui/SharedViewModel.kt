package com.example.currentweather.ui

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currentweather.data.repository.HomeRepository
import com.example.currentweather.framework.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val locationHelper: LocationHelper,
): ViewModel() {

    init {
        locationHelper.getCurrentLocation(
            onSuccess = {
                getCurrentWeather(it)
            },
            onFailure = {
                Log.i("SharedViewModel:::", "Failed To Load")
            }
        )
    }

    fun getCurrentWeather(location: Location) {
        viewModelScope.launch{
            homeRepository.fetchCurrentWeather(
                latitude = location.latitude,
                longitude = location.longitude,
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SharedViewModel:::", "SharedViewModel destroyed")
    }
}
