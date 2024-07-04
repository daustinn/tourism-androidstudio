package com.example.tourism.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tourism.models.Place
import com.example.tourism.network.ApiPlaces
import com.example.tourism.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlacesViewModel: ViewModel() {

    var apiPlaces: ApiPlaces = RetrofitClient.retrofit

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places = _places.asStateFlow()

    private val _suggestionPlaces = MutableStateFlow<List<Place>>(emptyList())
    val suggestionPlaces = _suggestionPlaces.asStateFlow()

    private val _currentCategory = MutableStateFlow("all")
    val currentCategory = _currentCategory.asStateFlow()

    private val _selectedPlace = MutableStateFlow<Place?>(null)
    val selectedPlace = _selectedPlace.asStateFlow()

    init {
        fetchPlaces()
    }

    fun fetchPlaces() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.retrofit.getPlaces()
                }
                _places.value = response ?: emptyList()
            } catch (e: Exception) {
                Log.e("PlacesViewModel", "Error fetching places", e)
                _places.value = emptyList()  // Manejo de errores: actualizar con una lista vacía
            }
        }
    }

    fun fetchPlaceById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = withContext(Dispatchers.IO) {
                try {
                     RetrofitClient.retrofit.getPlace(id)
                } catch (e: Exception) {
                    Log.e("PlacesViewModel", "Error fetching place with id: $id", e)
                    null
                }
            }
            _selectedPlace.value = result
        }
    }
    fun fetchPlaceByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentCategory.value = category
            try {
                if(category !== "all") {
                    val response =  RetrofitClient.retrofit.getPlaceByCategory(category)
                    _places.value = response ?: emptyList()
                } else fetchPlaces()

            } catch (e: Exception) {
                Log.e("PlacesViewModel", "Error fetching place with category: $category", e)
            }
        }
    }
    fun fetchSuggestionPlaces(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = withContext(Dispatchers.IO) {
                try {
                    RetrofitClient.retrofit.getPlaceByCategory(category) ?: emptyList()
                } catch (e: Exception) {
                    Log.e("PlacesViewModel", "Error fetching places with category: $category", e)
                    emptyList()
                }
            }
            _suggestionPlaces.value = result
        }
    }
}