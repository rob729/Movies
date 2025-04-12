package com.rob729.movies.models.network

import androidx.annotation.Keep
import com.rob729.movies.Constants
import com.rob729.movies.models.INetworkCurator
import com.rob729.movies.models.ui.MovieUiModel
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
): INetworkCurator<MovieUiModel> {
    override fun transformToCuratedData(): MovieUiModel {
        return MovieUiModel(
            title = title,
            posterUrl = posterPath?.let { "${Constants.IMAGE_URL_PREFIX}$it" },
            description = overview
        )
    }
}