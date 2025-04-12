package com.rob729.movies.network

import com.rob729.movies.models.network.MovieListResponse
import retrofit2.Response
import retrofit2.Retrofit

class MovieService(retrofit: Retrofit) : MovieApi {

    private val movieApi by lazy { retrofit.create(MovieApi::class.java) }

    override suspend fun getMovies(
        language: String,
        apiKey: String
    ): Response<MovieListResponse> = movieApi.getMovies(language, apiKey)
}