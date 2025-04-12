package com.rob729.movies.models.ui

import java.io.Serializable

data class MovieUiModel(
    val title: String? = null,
    val posterUrl: String? = null,
    val description: String? = null
): Serializable
