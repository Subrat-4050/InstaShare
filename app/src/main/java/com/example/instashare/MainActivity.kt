package com.example.instashare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.instashare.ui.theme.InstashareTheme
import com.example.instashare.ui.navigation.Nav_Home_Env
import com.example.instashare.ui.screens.splash.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            InstashareApp()
        }
    }
}

@Composable
fun InstashareApp() {
    InstashareTheme {
        var showSplashScreen by remember { mutableStateOf(true) }
        
        if (showSplashScreen) {
            SplashScreen(
                onSplashFinished = { showSplashScreen = false }
            )
        } else {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Nav_Home_Env()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    InstashareApp()
}
