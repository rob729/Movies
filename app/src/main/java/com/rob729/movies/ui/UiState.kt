package com.rob729.movies.ui

sealed class UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>()
    data object Loading : UiState<Nothing>()
    data class Error(val message: String) : UiState<Nothing>()
}