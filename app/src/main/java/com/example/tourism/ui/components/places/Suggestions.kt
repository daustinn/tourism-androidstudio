package com.example.tourism.ui.components.places

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tourism.navigation.AppScreens
import com.example.tourism.viewmodel.PlacesViewModel

data class SuggestionPlaceData(
    val id : String,
    val title: String,
    val thumbnail : String
)

@Composable
fun SuggestionPlaces(
    prevPlaceId: String,
    category: String,
    viewModel: PlacesViewModel,
    navController: NavController
){

    LaunchedEffect(category) {
        viewModel.fetchSuggestionPlaces(category)
    }

    val places by viewModel.suggestionPlaces.collectAsState()

    val suggestionPlaces = remember(places) {
        places.filter { it.id != prevPlaceId }
            .map { place ->
                SuggestionPlaceData(
                    id = place.id,
                    title = place.title,
                    thumbnail = place.thumbnail
                )
            }
    }

    if(places.isNotEmpty()){
        Column {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Text(
                    text ="Talvez te pueda interesar",
                    color = Color(0xff282c34),
                    fontSize = 17.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                contentPadding = PaddingValues(horizontal = 3.dp)
            ) {
                items(suggestionPlaces) {
                    SuggestionPlace(it, navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionPlace(place: SuggestionPlaceData, navController: NavController) {
    Column(
        modifier = Modifier.width(70.dp)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xffe8e5d8)),
            shape = RoundedCornerShape(15.dp),
            onClick = {  navController.navigate(route = AppScreens.PlaceScreen.route + "/${place.id}") }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(place.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = place.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(3.dp) )
        Text(
            text = place.title,
            color = Color(0xff282c34),
            fontSize = 13.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSuggestionPlaces() {
    val viewModel = PlacesViewModel()
    val navController = rememberNavController()
    SuggestionPlaces("ID", "Category",viewModel, navController)
}
