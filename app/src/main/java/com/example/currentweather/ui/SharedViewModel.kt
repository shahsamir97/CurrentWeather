package com.example.currentweather.ui

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currentweather.data.model.toWeatherUiData
import com.example.currentweather.data.repository.SharedRepository
import com.example.currentweather.framework.LocationHelper
import com.example.currentweather.ui.screens.home.HomeEvents
import com.example.currentweather.ui.screens.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val sharedRepository: SharedRepository,
    private val locationHelper: LocationHelper,
): ViewModel(), HomeEvents {

    private val _homeState = MutableStateFlow(HomeState.default)
    val homeState: StateFlow<HomeState> = _homeState

    init {
        getCurrentLocation()
    }

    fun getCurrentLocation() {
        locationHelper.getCurrentLocation(
            onSuccess = {
                getCurrentWeather(it)
            },
            onFailure = {
                _homeState.update { it.copy(showAlert = true) }
            }
        )
    }

    fun getCurrentWeather(location: Location) {
        viewModelScope.launch{
            val weatherInfoDto = sharedRepository.fetchCurrentWeather(
                latitude = location.latitude,
                longitude = location.longitude,
            )

            if (weatherInfoDto != null) {
                _homeState.update { it.copy(
                    weatherData = weatherInfoDto.toWeatherUiData(),
                    showAlert = false,
                ) }
            } else {
                _homeState.update { it.copy(showAlert = true) }
            }
        }
    }

    override fun onClickTryAgain() {
        getCurrentLocation()
    }
}
