package com.rob729.movies.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rob729.movies.models.database.MovieData
import com.rob729.movies.repo.BaseRepository
import com.rob729.movies.repo.MovieRepository
import com.rob729.movies.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val state: StateFlow<UiState<HomeState>>
        get() = _state
    private val _state = MutableStateFlow<UiState<HomeState>>(UiState.Loading)

    init {
        viewModelScope.launch {
            _state.emit(
                when (val result = movieRepository.getMovies()) {
                    is BaseRepository.ApiResult.Error -> UiState.Error(result.message)
                    is BaseRepository.ApiResult.Success<List<MovieData>> -> UiState.Success<HomeState>(
                        data = HomeState(movies = result.data.map { it.toMovieUiModel() })
                    )
                }
            )
        }
    }
}