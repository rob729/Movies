package com.rob729.movies.repo

import com.rob729.movies.BuildConfig
import com.rob729.movies.utils.Constants
import com.rob729.movies.utils.NetworkHelper
import com.rob729.movies.database.MovieDao
import com.rob729.movies.models.database.MovieData
import com.rob729.movies.models.network.MovieListResponse
import com.rob729.movies.network.MovieService

class MovieRepository(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val networkHelper: NetworkHelper
) : BaseRepository() {

    suspend fun getMovies(): ApiResult<List<MovieData>> {
        if (!networkHelper.isConnectedToInternet()) {
            val cachedMovies = movieDao.getAllMovies()
            return if (cachedMovies.isNotEmpty()) {
                ApiResult.Success(cachedMovies)
            } else {
                ApiResult.Error(Constants.NO_DATA_ERROR_MESSAGE)
            }
        }

        return when (val response = handleResponse {
            movieService.getMovies(Constants.LANGUAGE, BuildConfig.IMDB_KEY)
        }) {
            is ApiResult.Error -> {
                ApiResult.Error(response.message)
            }
            is ApiResult.Success<MovieListResponse> -> processMoviesResponse(response.data)
        }
    }

    private suspend fun processMoviesResponse(response: MovieListResponse): ApiResult<List<MovieData>> {
        val movies = response.results?.mapNotNull { it.toMovieData() }.orEmpty()
        return if (movies.isEmpty()) {
            ApiResult.Error(Constants.NO_MOVIES_FOUND_MESSAGE)
        } else {
            ApiResult.Success(movies).also {
                movieDao.insertMovies(movies)
            }
        }
    }

    suspend fun getMovieDataById(movieId: Long) = movieDao.getMovieById(movieId)
}