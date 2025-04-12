package com.rob729.movies.repo

import com.rob729.movies.BuildConfig
import com.rob729.movies.Constants
import com.rob729.movies.network.MovieService

class MovieRepository(private val movieService: MovieService): BaseRepository() {

    suspend fun getMovies() = handleResponse(movieService.getMovies(Constants.LANGUAGE, BuildConfig.IMDB_KEY))
}