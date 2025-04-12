package com.rob729.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rob729.movies.ui.feature.details.DetailsScreen
import com.rob729.movies.ui.feature.home.HomeScreen
import com.rob729.movies.ui.theme.MoviesTheme
import com.rob729.movies.utils.Constants

class MoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MoviesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = NavigationScreens.HOME.name,
                            modifier = Modifier.padding(paddingValues = innerPadding)
                        ) {
                            composable(NavigationScreens.HOME.name) {
                                HomeScreen(navController)
                            }

                            composable(
                                "${NavigationScreens.DETAILS.name}/{${Constants.PATH_PARAM_MOVIE_ID}}",
                                listOf(navArgument(Constants.PATH_PARAM_MOVIE_ID) { type = NavType.LongType })) { backStackEntry ->
                                val movieId = backStackEntry.arguments?.getLong(Constants.PATH_PARAM_MOVIE_ID)
                                movieId?.let {
                                    DetailsScreen(movieId, navController)
                                }
                            }
                        }
                }
            }
        }
    }
}