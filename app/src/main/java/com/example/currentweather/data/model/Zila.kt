package com.example.currentweather.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Zila(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val state: String,
) : Parcelable
