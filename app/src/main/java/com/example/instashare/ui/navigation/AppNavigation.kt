package com.example.instashare.ui.navigation

import android.provider.ContactsContract
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        composable("environment_dashboard") {
            Environment(navController, false)
        }
        composable("profile") {
            UserProfile(navController)
        }
    }
}