package com.example.currentweather.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.currentweather.framework.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    locationHelper: LocationHelper
): ViewModel() {

    init {
        Log.i("SharedViewModel:::", "SharedViewModel created")
        locationHelper.getCurrentLocation(onSuccess = {
            Log.i("SharedViewModel:::", "${it.longitude} ${it.latitude}")
        },
            onFailure = {}
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SharedViewModel:::", "SharedViewModel destroyed")
    }
}