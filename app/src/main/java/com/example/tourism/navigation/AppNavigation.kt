package com.example.tourism.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tourism.screens.HomeScreen
import com.example.tourism.screens.PlaceScreen
import com.example.tourism.ui.components.Header
import com.example.tourism.viewmodel.PlacesViewModel

@Composable
fun AppNavigation(viewModel: PlacesViewModel,) {

    val navController = rememberNavController()
    Column {
        Header(navController)
        NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
            composable(route = AppScreens.HomeScreen.route){
                HomeScreen(viewModel, navController)
            }
            composable(route = AppScreens.PlaceScreen.route + "/{id_place}", arguments = listOf(
                navArgument(name="id_place"){
                    type = NavType.StringType
                }
            )){
                PlaceScreen(viewModel, navController, it.arguments?.getString("id_place") ?: "default_id")
            }
        }
    }

}