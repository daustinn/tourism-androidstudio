package com.example.tourism.ui.components.places

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tourism.viewmodel.PlacesViewModel

@Composable
fun Places(viewModel: PlacesViewModel, navController: NavController){

    val places by viewModel.places.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().padding(10.dp),
    ) {
        items(places) {
            PlaceCard(it, navController)
        }
    }
}

