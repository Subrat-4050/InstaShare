package com.example.instashare.ui.Sharing

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun ShareTextToOtherApps(context: Context, shareCode: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "Here is my share code of InstaShare: $shareCode")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Code")
    context.startActivity(shareIntent)
}

