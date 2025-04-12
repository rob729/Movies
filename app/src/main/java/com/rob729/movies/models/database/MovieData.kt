package com.rob729.movies.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rob729.movies.models.ui.MovieUiModel
import java.io.Serializable

@Entity(tableName = "movies_table")
data class MovieData(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_url") val posterUrl: String,
    @ColumnInfo(name = "description") val description: String
) : Serializable {

    fun toMovieUiModel() = MovieUiModel(id, title, posterUrl, description)
}
