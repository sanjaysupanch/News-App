package com.example.news.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val route : String) {
    @Serializable
    data object Home : Screens("home_screen")

    @Serializable
    data object Saved : Screens("save_screen")

    @Serializable
    data object Details : Screens("details_screen")
}
