package com.rob729.movies.network

import com.rob729.movies.models.network.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/trending/movie/week")
    suspend fun getMovies(
        @Query("language") language: String,
        @Query("api_key") apiKey: String,
    ): Response<MovieListResponse>
}