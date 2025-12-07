package com.example.instashare.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instashare.ui.theme.Pink40
import com.example.instashare.ui.theme.Purple40
import com.example.instashare.ui.theme.Purple80
import com.example.instashare.ui.theme.PurpleGrey40

@Composable
fun UserProfile(navController: NavController) {
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
            .padding(12.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(0.dp)
        ) {
            UserAvatar("Subrat Sahoo")
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

