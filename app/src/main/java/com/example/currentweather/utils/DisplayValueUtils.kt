package com.example.currentweather.utils

import androidx.compose.runtime.Composable

@Composable
fun String.toUiFormatedValue(): String {
    return if (this.isEmpty()) {
        "N/A"
    } else {
        this
    }
}