package com.example.currentweather.ui.screens.home

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currentweather.data.model.toWeatherUiData
import com.example.currentweather.data.repository.SharedRepository
import com.example.currentweather.framework.LocationHelper
import com.example.currentweather.ui.navigation.AppScreens
import com.example.currentweather.ui.navigation.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedRepository: SharedRepository,
    private val locationHelper: LocationHelper,
): ViewModel(), HomeEvents {

    private val _homeState = MutableStateFlow(HomeState.default)
    val homeState: StateFlow<HomeState> = _homeState

    private val _navigationEvent = MutableSharedFlow<NavigationRoute>()
    val navigationEvent: SharedFlow<NavigationRoute> = _navigationEvent

    init {
        getCurrentLocation()
    }

    fun getCurrentLocation() {
        locationHelper.getCurrentLocation(
            onSuccess = {
                getCurrentWeather(it.latitude, it.longitude)
            },
            onFailure = {
                _homeState.update { it.copy(showAlert = true) }
            }
        )
    }

    fun getCurrentWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch{
            val weatherInfoDto = sharedRepository.fetchCurrentWeather(
                latitude = latitude,
                longitude = longitude,
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

    override fun onClickSearch() {
        viewModelScope.launch {
            _navigationEvent.emit(AppScreens.Search)
        }
    }
}
