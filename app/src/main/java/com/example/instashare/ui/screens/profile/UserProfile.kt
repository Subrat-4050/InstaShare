package com.example.instashare.ui.screens.profile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun UserProfile(navController: NavController) {
    var selectedImageUri by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            Log.d("UserProfile", "Selected image URI: $uri")
        }
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF3F51B5).copy(alpha = 0.8f),
                        Color.White.copy(alpha = 0.9f)
                    )
                )
            )
            .padding(12.dp, 60.dp, 12.dp, 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(0.dp)
        ) {
            UserAvatar(selectedImageUri,"Subrat Sahoo", photoPickerLauncher)
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF3848A2)
                )
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Home",
                        modifier = Modifier
                            .background(color = Color.White, shape = CircleShape)
                            .size(40.dp),
                        tint = Color(0xFF3848A2)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Back to",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Home",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

            Text(
                text = "Account Information",
                color = Color(0xFF3848A2),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, top = 8.dp)
            )

            Column {
                ProfileInfo(Icons.Default.Person, "Name", "Subrat Sahoo")
                ProfileInfo(Icons.Default.Phone, "Mobile", "+91 7008812345")
                ProfileInfo(Icons.Default.Email, "Email", "developer@gmail.com")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserProfile() {
     UserProfile(navController = rememberNavController())
}