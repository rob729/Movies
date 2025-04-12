package com.rob729.movies.models.network

import androidx.annotation.Keep
import com.rob729.movies.utils.Constants
import com.rob729.movies.models.database.MovieData
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
    @Json(name = "id") val id: Long? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "overview") val overview: String? = null,
) {
    fun toMovieData(): MovieData? {
        if (id == null || title == null || posterPath == null || overview == null) {
            return null
        }

        return MovieData(
            id = id,
            title = title,
            posterUrl = "${Constants.IMAGE_URL_PREFIX}$posterPath",
            description = overview
        )
    }
}