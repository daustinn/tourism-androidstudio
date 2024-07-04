package com.example.tourism.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tourism.R
import com.example.tourism.navigation.AppScreens
import com.example.tourism.ui.components.profile.ProfileButton
import com.example.tourism.viewmodel.PlacesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFD3D3D3), // Color del borde inferior
                shape = RectangleShape
            )
            .padding(vertical = 8.dp, horizontal = 10.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Card (
                onClick = { navController.navigate(route = AppScreens.HomeScreen.route)}
            ){
                Image(
                    painter = painterResource(id = R.drawable.favicon),
                    contentDescription = "Favicon",
                    modifier = Modifier
                        .size(40.dp),
                )
            }
            Text(
                text = "Arc",
                color = Color(0xff3d3929),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = Color(0xff949185),
                modifier = Modifier.size(28.dp)
            )
            ProfileButton()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    val navController = rememberNavController()
    Header(navController)
}
