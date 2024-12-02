package com.example.currentweather.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currentweather.data.model.Zila
import com.example.currentweather.data.repository.SharedRepository
import com.example.currentweather.ui.navigation.AppScreens
import com.example.currentweather.ui.navigation.NavigateBack
import com.example.currentweather.ui.navigation.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val sharedRepository: SharedRepository,
): ViewModel(), SearchScreenEvents {

    private val _searchScreenState = MutableStateFlow(SearchScreenState.default)
    val searchScreenState: StateFlow<SearchScreenState> = _searchScreenState

    private val _navigationEvent = MutableSharedFlow<NavigationRoute>()
    val navigationEvent: SharedFlow<NavigationRoute> = _navigationEvent

    private var listOfZila = emptyList<Zila>()

    var selectedZila: Zila? =  null


    init {
        viewModelScope.launch {
            listOfZila = sharedRepository.fetchZilaList()
            _searchScreenState.update { it.copy(listOfZila = listOfZila.take(10)) }
        }
    }

    override fun onSearchQuery(queryText: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val filteredZilaList = listOfZila.filter { it.name.contains(queryText) }.take(10)
            _searchScreenState.update {
                it.copy(
                    searchText = queryText,
                    listOfZila = filteredZilaList,
                )
            }
        }
    }

    override fun onClickZila(zila: Zila) {
        selectedZila = zila
        viewModelScope.launch {
            _navigationEvent.emit(AppScreens.Home)
        }
    }

    override fun onNavigateBack() {
        viewModelScope.launch {
            _navigationEvent.emit(NavigateBack)
        }
    }
}