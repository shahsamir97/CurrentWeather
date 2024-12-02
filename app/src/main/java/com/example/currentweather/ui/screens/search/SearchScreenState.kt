package com.example.currentweather.ui.screens.search

import com.example.currentweather.data.model.Zila

data class SearchScreenState(
    val searchText: String,
    val listOfZila: List<Zila>
) {
    companion object {
        val default = SearchScreenState(
            listOfZila = emptyList(),
            searchText = ""
        )
    }
}
