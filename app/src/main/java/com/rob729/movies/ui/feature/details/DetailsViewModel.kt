package com.rob729.movies.ui.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rob729.movies.models.ui.MovieUiModel
import com.rob729.movies.repo.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movieDetailsFlow: StateFlow<MovieUiModel?>
        get() = _movieDetailsFlow
    private val _movieDetailsFlow = MutableStateFlow<MovieUiModel?>(null)

    fun fetchMovieData(movieId: Long) {
        viewModelScope.launch {
            _movieDetailsFlow.emit(movieRepository.getMovieDataById(movieId)?.toMovieUiModel())
        }
    }
}