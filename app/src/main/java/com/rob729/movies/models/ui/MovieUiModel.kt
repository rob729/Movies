package com.rob729.movies.models.ui

import java.io.Serializable

data class MovieUiModel(
    val id: Long,
    val title: String,
    val posterUrl: String,
    val description: String,
): Serializable
