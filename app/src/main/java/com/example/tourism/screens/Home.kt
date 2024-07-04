package com.example.tourism.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tourism.navigation.AppScreens
import com.example.tourism.ui.components.Categories
import com.example.tourism.ui.components.places.Places
import com.example.tourism.viewmodel.PlacesViewModel

@Composable
fun HomeScreen(viewModel: PlacesViewModel, navController: NavController) {
    Scaffold(
        topBar =  {

        }
    ) {
        BodyContent(viewModel, navController, it)
    }
}

@Composable
fun BodyContent(viewModel: PlacesViewModel, navController: NavController, pad: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfff6f5f0))
    ) {
        Categories(viewModel)
        Places(viewModel, navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val placesViewModel = PlacesViewModel()
    val navController = rememberNavController()
    HomeScreen(placesViewModel,navController)
}
