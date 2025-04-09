package com.ag.project.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
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
                HomeScreen(
                    navHostController = navController,
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }

            composable<Screen.Details> {

                val args = it.toRoute<Screen.Details>()
                DetailsScreen(
                    productId = args.id,
                    navHostController = navController,
                    animatedVisibilityScope = this,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }

}