package com.example.currentweather.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currentweather.R
import com.example.currentweather.data.model.Coord
import com.example.currentweather.data.model.Zila
import com.example.currentweather.ui.theme.CurrentWeatherTheme

@Composable
fun SearchScreen(state: SearchScreenState, events: SearchScreenEvents) {
    SearchScreenContent(state = state, events = events)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(state: SearchScreenState, events: SearchScreenEvents) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.searchText,
                        onValueChange = { events.onSearchQuery(it) },
                        placeholder = {
                            Text(text = stringResource(R.string.search_zila))
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { events.onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                })
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(state.listOfZila) {
                    ZilaListItem(
                        zila = it,
                        onClick = { events.onClickZila(it) }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}

@Composable
fun ZilaListItem(zila: Zila, onClick: () -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
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
                contentDescription = zila.name,
            )
            Spacer(Modifier.width(16.dp))
            Text(text = zila.name)
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    CurrentWeatherTheme {
        SearchScreenContent(
            state = SearchScreenState.default.copy(listOfZila = listOf(
                Zila(coord = Coord(1.0,1.0), country = "BD", id = 1, name = "Dhaka", state = "Dhaka"),
                Zila(coord = Coord(1.0,1.0), country = "BD", id = 1, name = "Dhaka", state = "Dhaka")
            )),
            events = object : SearchScreenEvents {
                override fun onSearchQuery(queryText: String) {}
                override fun onClickZila(zila: Zila) {}
                override fun onNavigateBack() {}
            }
        )
    }
}