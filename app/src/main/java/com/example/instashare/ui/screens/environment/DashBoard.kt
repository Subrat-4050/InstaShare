package com.example.chatui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.instashare.ui.screens.environment.BubbleAlignment
import com.example.instashare.ui.screens.environment.chatBubble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var messageText by remember { mutableStateOf("") }

    var messageList by remember { mutableStateOf(listOf<String>("Hello", "Hii")) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6), RoundedCornerShape(12.dp))
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(16.dp)
                .zIndex(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color.Gray, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Chat Icon",
                        tint = Color.White
                    )
                }
                Spacer(
                    modifier = Modifier.width(12.dp)
                )
                Text(
                    text = "1232",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
            }

            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "View Photos",
                    tint = Color.Black
                )
            }
        }

        // Main content area
//        Box(
//            modifier = Modifier
//                .align(alignment = Alignment.BottomStart)
//                .padding(bottom = 90.dp)
//                .chatBubble(BubbleAlignment.LEFT, corners = 12.dp, tailwidth = 6.dp, tailheight = 8.dp)
//        ) {
//            Text(
//                text = "Hello! This is the chat area.",
//                modifier = Modifier.padding(12.dp, 8.dp)
//            )
//        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(messageList) { message ->
                    Box(
                        modifier = Modifier
                            .chatBubble(BubbleAlignment.LEFT, corners = 12.dp, tailwidth = 6.dp, tailheight = 8.dp)
                    ) {
                        Text(
                            text = message,
                            modifier = Modifier.padding(12.dp, 8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }

        // Bottom input section
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Input field with attachment button
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            placeholder = {
                                Text(
                                    "type Anything",
                                    color = Color(0xFFB0B0B0),
                                    fontSize = 14.sp
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedTextColor = Color.Black,
                                focusedTextColor = Color.Black
                            ),
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AttachFile,
                                contentDescription = "Attach file",
                                tint = Color(0xFF666666)
                            )
                        }
                    }
                }

                // Send button
                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = if (messageText.isEmpty()) Color.White else Color(0xFF5C6BC0),
                    shadowElevation = 2.dp,
                    onClick = {
                        if (messageText.isNotEmpty()) {
                            messageList = messageList + messageText
                        }
                    }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = if (messageText.isEmpty())
                                Color(0xFF666666)
                            else
                                Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreen()
    }
}