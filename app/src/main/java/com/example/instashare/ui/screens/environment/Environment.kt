package com.example.instashare.ui.screens.environment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.tv.material3.Text
import com.example.chatui.ChatScreen

@Composable
fun Environment(navController: NavController) {
    var selectedEnvironment by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A237E))
            .padding(16.dp, 64.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            TextButton(
                modifier = Modifier
                    .background(Color(
                        if(selectedEnvironment)
                                    0xFF3DC0B2
                        else
                                    0xFFFF
                    ), shape = RoundedCornerShape(12.dp))
                    .weight(1f),
                onClick = {
                    selectedEnvironment = true
                }
            ) {
                Text(text = "All Partners")
            }
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        Color(
                            if(!selectedEnvironment)
                                0xFF3DC0B2
                            else
                                0xFFFF
                        ), shape = RoundedCornerShape(12.dp)
                    ),
                onClick = {
                    selectedEnvironment = false
                }
            ) {
                Text(text = "Go to Dashboard")
            }
        }

        // Chart Box / Partners List
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            if (selectedEnvironment) {
                PartnersList()
            } else {
                ChatScreen()
            }
        }
    }
}

@Preview
@Composable
private fun EnvironmentPreview() {
    Environment(
        navController = NavController(LocalContext.current)
    )
}