package com.rob729.movies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rob729.movies.models.database.MovieData

@Dao
interface MovieDao {

    @Query("SELECT * from movies_table")
    suspend fun getAllMovies(): List<MovieData>

    @Query("SELECT * from movies_table where id = :movieId")
    suspend fun getMovieById(movieId: Long): MovieData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieData>)

    @Query("SELECT * from movies_table where title LIKE '%' || :query || '%'")
    suspend fun getMoviesByTitle(query: String): List<MovieData>
}