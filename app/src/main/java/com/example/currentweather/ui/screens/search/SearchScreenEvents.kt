package com.example.currentweather.ui.screens.search

import com.example.currentweather.data.model.Zila

interface SearchScreenEvents {
    fun onSearchQuery(queryText: String)
    fun onClickZila(zila: Zila)
    fun onNavigateBack()
}