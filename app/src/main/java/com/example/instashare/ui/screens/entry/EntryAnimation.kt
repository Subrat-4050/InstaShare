package com.example.instashare.ui.screens.entry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instashare.R

@Composable
fun LoginHeader() {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFF3DC0B2),
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.wave_vector1),
            contentDescription = "Wave Vector1"
        )
        Image(
            painter = painterResource(id = R.drawable.wave_vector2),
            contentDescription = "Wave Vector2"
        )
        Image(
            painter = painterResource(id = R.drawable.wave_vector3),
            contentDescription = "Wave Vector3"
        )
    }
}

@Preview
@Composable
private fun PreviewLoginHeader() {
    LoginHeader()
}