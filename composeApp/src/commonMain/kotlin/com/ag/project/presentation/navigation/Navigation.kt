package com.ag.project.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
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
import androidx.navigation.toRoute
import com.ag.project.presentation.screen.details.DetailsScreen
import com.ag.project.presentation.screen.home.HomeScreen
import com.ag.project.util.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()

    SharedTransitionLayout {

        NavHost(
            navController = navController,
            startDestination = Screen.Home,
        ) {

            composable<Screen.Home> {
                HomeScreen(navHostController = navController)
            }

            composable<Screen.Details> {

                val args = it.toRoute<Screen.Details>()
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {


                    DetailsScreen(
                        productId = args.id,
                        animatedVisibilityScope = this
                    )
//                }
            }

        }
    }

}