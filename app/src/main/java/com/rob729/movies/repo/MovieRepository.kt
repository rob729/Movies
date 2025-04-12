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
        return if (networkHelper.isConnectedToInternet()) {
            when (val response =
                handleResponse(movieService.getMovies(Constants.LANGUAGE, BuildConfig.IMDB_KEY))) {
                is ApiResult.Error -> ApiResult.Error(response.throwable)
                is ApiResult.Success<MovieListResponse> -> {
                    val movies = response.data.results?.mapNotNull { it.toMovieData() }
                    if (movies.isNullOrEmpty()) {
                        ApiResult.Error(Throwable("No movies found"))
                    } else {
                        ApiResult.Success(data = movies).also {
                            movieDao.insertMovies(movies)
                        }
                    }
                }
            }
        } else {
            val movies = movieDao.getAllMovies()
            ApiResult.Success(movies).takeIf { movies.isNotEmpty() } ?: run {
                ApiResult.Error(Throwable("No internet connection and no cached data available."))
            }
        }
    }

    suspend fun getMovieDataById(movieId: Long) = movieDao.getMovieById(movieId)
}