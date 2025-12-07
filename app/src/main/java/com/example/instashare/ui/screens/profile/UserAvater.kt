package com.example.instashare.ui.screens.profile

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instashare.R

@Composable
fun UserAvatar(userName: String = "No Name") {
    Column {
        Box(
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                modifier = Modifier
                    .scale(1.5f),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.user),
                contentDescription = "User Avatar for $userName"
            )
        }
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Text(
            text = "$userName",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun PreviewUserAvatar() {
    UserAvatar("Sonu")
}