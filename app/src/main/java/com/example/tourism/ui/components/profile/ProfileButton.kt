package com.example.tourism.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tourism.R
@Composable
fun ProfileButton() {
    var showModal by remember { mutableStateOf(false) }

    IconButton(
        onClick = { showModal = true },
        modifier = Modifier
            .size(35.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile), // Reemplaza con el nombre de tu imagen
            contentDescription = "Profile Picture",
            modifier = Modifier
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }

    if (showModal) {
        ProfileDialog(onDismiss = { showModal = false })
    }
}


