package com.example.tourism.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("home")
    object PlaceScreen: AppScreens("place")

}