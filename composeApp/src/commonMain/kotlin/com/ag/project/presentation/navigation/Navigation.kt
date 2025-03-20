package com.ag.project.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ag.project.presentation.screen.home.HomeScreen
import com.ag.project.util.Screen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home,
    ) {

        composable<Screen.Home> {
            HomeScreen(navHostController = navController)
        }

        composable<Screen.Details> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier
                        .clickable {
                            navController.navigateUp()
                        }
                )
                Text("Navigate Successfully")
            }
        }

    }
}