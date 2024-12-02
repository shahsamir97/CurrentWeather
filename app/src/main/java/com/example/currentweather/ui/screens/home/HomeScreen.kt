package com.example.currentweather.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.currentweather.R
import com.example.currentweather.ui.components.AlertDialog
import com.example.currentweather.ui.theme.CurrentWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(state: HomeState, events: HomeEvents) {
    val context = LocalContext.current

    val locationPermissionState = rememberMultiplePermissionsState(listOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ))

    LaunchedEffect(locationPermissionState.allPermissionsGranted) {
        when(locationPermissionState.allPermissionsGranted) {
            true -> {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            false -> {
                locationPermissionState.launchMultiplePermissionRequest()
            }
        }
    }

    HomeScreenContent(state = state, events = events)
}

@Composable
fun HomeScreenContent(state: HomeState, events: HomeEvents) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_location_on_24),
                        contentDescription = state.weatherData.cityName,
                    )
                    Spacer(Modifier.width(16.dp))
                    Text(text = state.weatherData.cityName.ifEmpty {
                        stringResource(R.string.search_zila)
                    })
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 28.dp)
                ) {
                    Text(
                        stringResource(R.string.now),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (state.weatherData.mainTemp.isNotEmpty()) {
                                stringResource(R.string.temperature, state.weatherData.mainTemp)
                            } else stringResource(R.string.n_a),
                            style = MaterialTheme.typography.displayLarge,
                            fontWeight = FontWeight.W900,
                        )
                        AsyncImage(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            model = state.weatherData.iconUrl,
                            contentDescription = state.weatherData.description,
                            placeholder = painterResource(R.drawable.baseline_image_24),
                            error = painterResource(R.drawable.baseline_broken_image_24),
                        )
                        Spacer(Modifier.weight(1f))
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = state.weatherData.description.ifEmpty { stringResource(R.string.n_a) },
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = if (state.weatherData.tempFeelsLike.isNotEmpty()) {
                                    stringResource(R.string.feels_like_x, 25)
                                } else stringResource(R.string.n_a),
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                    }
                    Text(
                        text = if (state.weatherData.minTemp.isEmpty() || state.weatherData.maxTemp.isEmpty()) {
                            stringResource(R.string.n_a)
                        } else {
                            stringResource(R.string.high_x_low_x, state.weatherData.maxTemp, state.weatherData.minTemp)
                        },
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }

        if (state.showAlert) {
            AlertDialog(
                onDismissRequest = {},
                title = stringResource(R.string.something_went_wrong),
                actionButtonTitle = stringResource(R.string.try_again),
                onClickActionButton = { events.onClickTryAgain() },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CurrentWeatherTheme {
        HomeScreenContent(
            state = HomeState.default,
            events = object : HomeEvents {
                override fun onClickTryAgain() {}
            }
        )
    }
}
