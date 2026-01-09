package com.example.chatui

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.instashare.models.Message
import com.example.instashare.models.MessageType
import com.example.instashare.models.sampleMessage1
import com.example.instashare.models.sampleMessage2
import com.example.instashare.ui.screens.environment.BubbleAlignment
import com.example.instashare.ui.screens.environment.chatBubble
import kotlin.comparisons.then

lateinit var messageStructure: Message

fun Context.saveToDownloads(uri: Uri, filename: String) {
    contentResolver.openInputStream(uri)?.use { inputStream ->
        val downloadsFolder = android.os.Environment.getExternalStoragePublicDirectory(
            android.os.Environment.DIRECTORY_DOWNLOADS
        )
        val file = java.io.File(downloadsFolder, filename)
        file.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }
}

fun getFilenameFromUri(uri: Uri): String {
    return uri.lastPathSegment ?: "unknown"
}

fun Context.getFilename(uri: Uri): String? {
    return contentResolver.query(
        uri, null, null, null, null
    )?.use { cursor ->
        cursor.moveToFirst()
        cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME).let {
            cursor.getString(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var messageText by remember { mutableStateOf("") }
    var isFile by remember { mutableStateOf(false) }
    var messageList by remember { mutableStateOf(listOf<Message>(sampleMessage1, sampleMessage2)) }
    val context = LocalContext.current
    val fileUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    // Add LazyListState and coroutineScope
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll to bottom when messages change
    LaunchedEffect(messageList.size) {
        if (messageList.isNotEmpty()) {
            listState.animateScrollToItem(messageList.size - 1)
        }
    }

    val pickFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val filename = context.getFilename(it) ?: "unknown"
            context.saveToDownloads(it, filename)
            Toast.makeText(context, getFilenameFromUri(it), Toast.LENGTH_SHORT).show()
            messageText = context.getFilename(it) ?: "unknown"
            isFile = true
            fileUri.value = it
        }
    }

    Column(
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
                    text = "1232", style = MaterialTheme.typography.titleLarge, color = Color.Black
                )
            }

            IconButton(
                onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "View Photos",
                    tint = Color.Black
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                items(messageList) { message ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalArrangement = if (message.type == MessageType.FILE) Arrangement.End else Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .widthIn(max = 280.dp)
                                .chatBubble(
                                    if (message.type == MessageType.FILE) BubbleAlignment.RIGHT else BubbleAlignment.LEFT,
                                    corners = 12.dp,
                                    tailwidth = 6.dp,
                                    tailheight = 8.dp
                                )
                                .then(
                                    if (message.type == MessageType.FILE) Modifier.clickable {
                                        Toast.makeText(context, "File clicked", Toast.LENGTH_SHORT).show()
                                        message.fileUri?.let { uri ->
                                            val filename = context.getFilename(uri) ?: "unknown"
                                            context.saveToDownloads(uri, filename)
                                            Toast.makeText(context, "File saved to Downloads: $filename", Toast.LENGTH_SHORT).show()
                                        }
                                    } else Modifier
                                )
                        ) {
                            if (message.type == MessageType.FILE) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(12.dp, 8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Photo,
                                        contentDescription = "File Icon",
                                        tint = Color.Black,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = message.content, fontSize = 16.sp)
                                }
                            } else {
                                Text(
                                    text = message.content,
                                    modifier = Modifier.padding(12.dp, 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Bottom input section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = messageText,
                            onValueChange = { messageText = it },
                            placeholder = {
                                Text(
                                    "type Anything", color = Color(0xFFB0B0B0), fontSize = 14.sp
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
                                pickFileLauncher.launch("*/*")
                            }) {
                            Icon(
                                imageVector = Icons.Default.AttachFile,
                                contentDescription = "Attach file",
                                tint = Color(0xFF666666)
                            )
                        }
                    }
                }

                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = if (messageText.isEmpty()) Color.White else Color(0xFF5C6BC0),
                    shadowElevation = 2.dp,
                    onClick = {
                        if (messageText.isNotEmpty() && messageText.isNotBlank()) {
                            val messageStructure = Message(
                                id = messageList.size + 1,
                                senderId = "user1",
                                receiverId = "user2",
                                content = messageText,
                                timestamp = System.currentTimeMillis(),
                                type = if (isFile) MessageType.FILE else MessageType.TEXT,
                                fileUri = fileUri.value
                            )
                            messageList = messageList + messageStructure
                        }
                        messageText = ""
                        isFile = false
                    }) {
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = if (messageText.isEmpty()) Color(0xFF666666)
                            else Color.White
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