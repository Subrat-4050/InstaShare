package com.example.instashare.ui.screens.profile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun UserAvatar(selectedImageUri: Uri?, userName: String = "No Name", photoPickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>) {
    Column {
        Box(
            modifier = Modifier
                .size(110.dp)
                .border(3.dp, Color(0xFF003366), CircleShape)
                .padding(6.dp)
                .clip(CircleShape)
                .background(Color.Gray)
                .align(alignment = Alignment.CenterHorizontally)
                .clickable {
                        photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                }
        ) {
            Log.d("UserAvatar", "Displaying image URI: $selectedImageUri")
            if (selectedImageUri != null) {
                AsyncImage(
                    modifier = Modifier
                        .scale(1.5f),
                    contentScale = ContentScale.Crop,
                    model = selectedImageUri,
                    contentDescription = "Profile Image"
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Default Profile Icon",
                    modifier = Modifier.size(60.dp).align(Alignment.Center),
                    tint = Color.White.copy(alpha = 0.7f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = userName,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

//@Preview
//@Composable
//private fun PreviewUserAvatar() {
//    UserAvatar("Sonu")
//}