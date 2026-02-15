package com.mirlink.news.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mirlink.news.presentation.screens.details.DetailsScreen
import com.mirlink.news.presentation.screens.favorite.FavoriteScreen
import com.mirlink.news.presentation.screens.news.NewsScreen


@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.News.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(BottomNavItem.News.route) {
                NewsScreen(
                    onNewsClick = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }

            composable(BottomNavItem.Favorites.route) {
                FavoriteScreen(
                    onNewsClick = { id ->
                        navController.navigate("details/$id")
                    }
                )
            }
            composable(
                route = "details/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                )
            ) { backStackEntry ->

                val id = backStackEntry.arguments?.getString("id") ?: ""

                DetailsScreen(
                    newsId = id,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }

}