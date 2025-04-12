package com.rob729.movies.ui.feature.home

import com.rob729.movies.models.ui.MovieUiModel

data class HomeState(
    val movies: List<MovieUiModel> = emptyList(),
)
