package com.rob729.movies.models.network

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "results") val results: List<MovieItem>? = null,
)

@Keep
@JsonClass(generateAdapter = true)
data class MovieItem (
    @Json(name = "title") val title: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "overview") val overview: String? = null,
)