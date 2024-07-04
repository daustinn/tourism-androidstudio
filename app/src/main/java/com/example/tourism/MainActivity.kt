package com.example.tourism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import com.example.tourism.ui.theme.AppTheme
import com.example.tourism.navigation.AppNavigation
import com.example.tourism.viewmodel.PlacesViewModel

class MainActivity : ComponentActivity() {
    val viewModel: PlacesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xfff6f5f0)
                ) {
                    AppNavigation(viewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val placesViewModel = PlacesViewModel()
    AppTheme {
        AppNavigation(placesViewModel)
    }
}
