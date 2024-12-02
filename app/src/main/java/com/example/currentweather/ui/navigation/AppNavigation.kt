package com.example.currentweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.currentweather.data.model.Zila
import com.example.currentweather.ui.screens.home.HomeScreen
import com.example.currentweather.ui.screens.home.HomeViewModel
import com.example.currentweather.ui.screens.search.SearchScreen
import com.example.currentweather.ui.screens.search.SearchViewModel
import com.example.currentweather.utils.SELECTED_ZILA

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.route
    ) {
        composable(AppScreens.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val state by homeViewModel.homeState.collectAsStateWithLifecycle()
            val selectedZila = navController.currentBackStackEntry?.savedStateHandle?.get<Zila>(
                SELECTED_ZILA
            )

            if (selectedZila != null) {
                selectedZila.coord.let { homeViewModel.getCurrentWeather(it.lat, it.lon) }
            }

            LaunchedEffect(homeViewModel.navigationEvent) {
                homeViewModel.navigationEvent.collect {
                    when(it) {
                        AppScreens.Search -> {
                            navController.navigate(AppScreens.Search.route)
                        }

                        else -> Unit
                    }
                }
            }

            HomeScreen(state = state, events = homeViewModel)
        }

        composable(AppScreens.Search.route) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            val state by searchViewModel.searchScreenState.collectAsStateWithLifecycle()

            LaunchedEffect(searchViewModel.navigationEvent) {
                searchViewModel.navigationEvent.collect {
                    when(it) {
                        AppScreens.Home -> {
                            navController.previousBackStackEntry?.savedStateHandle?.set(
                                SELECTED_ZILA, searchViewModel.selectedZila
                            )
                            navController.navigateUp()
                        }

                        NavigateBack -> navController.navigateUp()

                        else -> Unit
                    }
                }
            }

            SearchScreen(state = state, events = searchViewModel)
        }
    }
}