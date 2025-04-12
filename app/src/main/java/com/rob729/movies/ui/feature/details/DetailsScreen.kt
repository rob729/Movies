package com.rob729.movies.ui.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.rob729.movies.utils.Utils
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieId: Long,
    navController: NavController,
    viewModel: DetailsViewModel = koinViewModel()
) {

    val movieDetails = viewModel.movieDetailsFlow.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieData(movieId)
    }

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.Gray,
                        contentDescription = "back"
                    )
                }
            },
            actions = { },
            modifier = Modifier.fillMaxWidth(),
        )

        movieDetails.value?.let { movie ->
            AsyncImage(
                model = Utils.getImageRequestModel(
                    LocalContext.current,
                    movie.posterUrl
                ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = movie.title, fontSize = 24.sp, modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = movie.description, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}