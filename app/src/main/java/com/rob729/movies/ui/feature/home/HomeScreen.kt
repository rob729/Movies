package com.rob729.movies.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rob729.movies.NavigationScreens
import com.rob729.movies.ui.UiState
import com.rob729.movies.ui.components.MovieItem
import com.rob729.movies.ui.components.SearchBar
import com.rob729.movies.utils.Constants
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
) {

    val homeState = viewModel.state.collectAsState()
    val listState = rememberLazyGridState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        when (val state = homeState.value) {
            is UiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.message, fontSize = 16.sp)
                }
            }

            UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success<HomeState> -> {
                SearchBar(Constants.SEARCH_PLACEHOLDER_TEXT) { query ->
                    viewModel.getMoviesByTitle(query)
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    state = listState,
                    contentPadding = PaddingValues(top = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.data.movies.size) { index ->
                        val movieData = state.data.movies[index]
                        MovieItem(movieData) {
                            navController.navigate("${NavigationScreens.DETAILS.name}/${movieData.id}")
                        }
                    }
                }
            }
        }

    }
}