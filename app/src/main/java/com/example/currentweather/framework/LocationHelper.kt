package com.example.currentweather.framework

import android.content.Context
import android.location.Location
import android.location.LocationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface LocationHelper {
    fun getCurrentLocation(onSuccess: (Location) -> Unit, onFailure: () -> Unit)
}

class LocationHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : LocationHelper {

    override fun getCurrentLocation(onSuccess: (Location) -> Unit, onFailure: () -> Unit) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (location != null) {
                onSuccess(location)
            } else {
                onFailure()
            }
        } catch (securityException: SecurityException) {
            onFailure()
        }
    }
}
