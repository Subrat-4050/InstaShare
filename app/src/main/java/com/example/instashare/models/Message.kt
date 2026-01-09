package com.example.instashare.models

import android.net.Uri

data class Message(
    val id: Int,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val timestamp: Long,
    val type: MessageType,
    val fileUri: Uri? = null
)

enum class MessageType {
    TEXT,
    FILE
}

// Sample messages
val sampleMessage1 =
    Message(
        id = 1,
        senderId = "user1",
        receiverId = "user2",
        content = "Hello! How are you?",
        timestamp = 1625247600000,
        type = MessageType.TEXT
    )
val sampleMessage2 =
    Message(
        id = 2,
        senderId = "user2",
        receiverId = "user1",
        content = "I'm good, thanks! Here's the file you asked for.",
        timestamp = 1625247660000,
        type = MessageType.FILE
    )
