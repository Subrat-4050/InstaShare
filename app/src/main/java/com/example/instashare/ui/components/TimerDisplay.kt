package com.example.instashare.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun TimerDisplay(
    modifier: Modifier = Modifier,
    initialTimeSeconds: Int = 900 // Default 5 minutes
) {
    var timeLeft by remember { mutableIntStateOf(initialTimeSeconds) }
    var isRunning by remember { mutableStateOf(true) }
    val initialTime = remember { initialTimeSeconds }

    // Timer coroutine
    LaunchedEffect(isRunning) {
        while (isRunning && isActive) {
            delay(1000L)
            if (timeLeft > 0) {
                timeLeft--
            } else {
                isRunning = false
            }
        }
    }

    // Calculate color based on time remaining
    val timerColor by animateColorAsState(
        targetValue = when {
            timeLeft.toFloat() / initialTime > 0.5f -> Color(0xFF4CAF50) // Green
            timeLeft.toFloat() / initialTime > 0.25f -> Color(0xFFFF9800) // Orange
            else -> Color(0xFFF44336) // Red
        },
        label = "timer_color"
    )

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = timerColor,
        shadowElevation = 2.dp
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formatTime(timeLeft),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

fun formatTime(seconds: Int): String {
    val mins = seconds / 60
    val secs = seconds % 60
    return "%02d:%02d".format(mins, secs)
}

@Preview
@Composable
private fun PreviewTimerDisplay() {
    TimerDisplay()
}