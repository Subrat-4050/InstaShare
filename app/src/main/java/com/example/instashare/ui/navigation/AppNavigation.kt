package com.example.instashare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatui.ChatScreen
import com.example.instashare.ui.screens.environment.Environment
import com.example.instashare.ui.screens.home.HomeScreen
import com.example.instashare.ui.screens.profile.UserProfile

@Composable
fun Nav_Home_Env(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("environment_parent_list") {
            Environment(navController)
        }
        composable("environment_dashboard/{roomCode}") { backstackEntry ->
            val roomCode = backstackEntry.arguments?.getString("roomCode");
            Environment(navController, false, roomCode ?: "0000")
        }
        composable("profile") {
            UserProfile(navController)
        }
        composable("dashboard") {
            ChatScreen(navController)
        }
    }
}
