package com.example.currentweather.ui

import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currentweather.data.model.Zila
import com.example.currentweather.data.repository.SharedRepository
import com.example.currentweather.framework.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val sharedRepository: SharedRepository,
    private val locationHelper: LocationHelper,
): ViewModel() {

    private val _listOfCities = MutableStateFlow<List<Zila>>(listOf())
    val listOfCities: StateFlow<List<Zila>> = _listOfCities

    init {
        locationHelper.getCurrentLocation(
            onSuccess = {
                getCurrentWeather(it)
            },
            onFailure = {
                Log.i("SharedViewModel:::", "Failed To Load")
            }
        )

        loadCities()
    }

    private fun loadCities() {
        viewModelScope.launch(Dispatchers.Default) {

        }
    }

    fun getCurrentWeather(location: Location) {
        viewModelScope.launch{
            sharedRepository.fetchCurrentWeather(
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
