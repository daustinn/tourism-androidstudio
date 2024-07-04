package com.example.tourism.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tourism.R
import com.example.tourism.models.Place
import com.example.tourism.navigation.AppScreens
import com.example.tourism.ui.components.Header
import com.example.tourism.ui.components.places.Places
import com.example.tourism.ui.components.places.SuggestionPlaces
import com.example.tourism.viewmodel.PlacesViewModel

@Composable
fun PlaceScreen(viewModel: PlacesViewModel, navController: NavController, id_place: String?) {
    PlaceBodyContent(viewModel,navController,id_place)
}


@Composable
fun PlaceBodyContent(viewModel: PlacesViewModel, navController: NavController, idPlace: String?) {

    LaunchedEffect(idPlace) {
        idPlace?.let {
            viewModel.fetchPlaceById(idPlace)
        }
    }

    val place by viewModel.selectedPlace.collectAsState()
    if(place != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xfff6f5f0))
                .padding(5.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Button(onClick = {
                navController.popBackStack()
            },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black),
                modifier = Modifier
                    .background(Color(0xfff6f5f0))
                    .padding(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back")
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Atras")
                }

            }

            GalleryPlace(listOf(place!!.thumbnail) + place!!.images)
            Spacer(modifier = Modifier.height(4.dp))
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                 Row(verticalAlignment = Alignment.CenterVertically) {
                     Icon(
                         imageVector = Icons.Default.Star,
                         contentDescription = null,
                         tint = Color(0xFFFFD700),
                         modifier = Modifier.size(23.dp)
                     )
                     Text(
                         text = place!!.rating.toString(),
                         color = Color(0xff282c34),
                         fontSize = 14.sp
                     )
                 }
                 Spacer(modifier = Modifier.height(10.dp))
                 Text(
                     text = place!!.title,
                     color = Color(0xff282c34),
                     fontSize = 20.sp,
                     fontWeight = FontWeight.Medium
                 )
                 Spacer(modifier = Modifier.height(10.dp))
                 Text(
                     text = place!!.description,
                     color = Color(0xff3c3f41),
                     fontSize = 15.sp,
                     fontWeight = FontWeight.Normal
                 )
                 Spacer(modifier = Modifier.height(10.dp))
                 Row(verticalAlignment = Alignment.CenterVertically) {
                     Icon(
                         imageVector = Icons.Default.Place,
                         contentDescription = null,
                         tint = Color(0xFF3c3f41),
                         modifier = Modifier.size(30.dp)
                     )
                     Spacer(modifier = Modifier.width(10.dp))
                     Text(
                         text = place!!.location,
                         color = Color(0xff505354),
                         fontSize = 14.sp
                     )
                 }
             }
            Spacer(modifier = Modifier.height(10.dp) )
            SuggestionPlaces(place!!.id, place!!.category, viewModel, navController)
        }
    }
}

@Composable
fun GalleryPlace(
    images: List<String>
) {
    // Galería de imágenes
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),

    ) {
        items(images) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp)
                    .border(1.dp, Color(0xffe1dcca), RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xffe8e5d8)),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp)

            ){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceScreen() {
    val placesViewModel = PlacesViewModel()
    val navController = rememberNavController()
    PlaceScreen(placesViewModel,navController,"this is a id_place")
}
