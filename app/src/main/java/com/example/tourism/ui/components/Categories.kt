package com.example.tourism.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tourism.R
import com.example.tourism.viewmodel.PlacesViewModel

data class Category(
    val icon : Int,
    val label : String,
    val value : String
)
@Composable
fun Categories(viewModel: PlacesViewModel) {
    val current by viewModel.currentCategory.collectAsState()
    val list = listOf(
        Category(R.drawable.all, "Todos", "all"),
        Category(R.drawable.churches, "Iglesias", "churches"),
        Category(R.drawable.museus, "Museos","museums"),
        Category(R.drawable.ruins, "Ruinas","ruins"),
        Category(R.drawable.parks, "Parques","parks"),
        Category(R.drawable.restaurants, "Restaurantes","restaurants"),
        Category(R.drawable.hotels, "Hoteles", "hotels"),
        Category(R.drawable.places, "Lugares", "places"),
        Category(R.drawable.events, "Eventos", "events"),
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xfff6f5f0))
            .padding(top = 10.dp)
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 3.dp)
    ) {
        items(list) {
            CategoryItem(
                item = it,
                viewModel,
                it.value === current
            )
        }
    }
}

@Composable
private fun CategoryItem(
    item: Category,
    viewModel: PlacesViewModel,
    isSelected: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .alpha(if (isSelected) 1.0f else 0.5f)
            .clickable(onClick = { viewModel.fetchPlaceByCategory(item.value) })
            .padding(horizontal = 10.dp)

    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .height(20.dp)
                .width(20.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = item.label,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if (isSelected) 1.0f else 0f)
                .height(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xff1e1f22)),
        ) {
            Text(
                text = item.label,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                color = Color.Transparent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceScreen() {
    val viewModel = PlacesViewModel()
    Categories(viewModel)
}
