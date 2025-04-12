package com.rob729.movies.repo

import retrofit2.Response

abstract class BaseRepository {

    sealed class ApiResult<out T> {
        data class Success<out T>(val data: T) : ApiResult<T>()
        data class Error(val throwable: Throwable) : ApiResult<Nothing>()
    }

    fun <T> handleResponse(response: Response<T>): ApiResult<T> {
        return runCatching {
            response.body().takeIf { response.isSuccessful }?.let {
                ApiResult.Success<T>(it)
            } ?: ApiResult.Error(Exception(response.message()))
        }.onFailure {
            ApiResult.Error(it)
        }.getOrDefault(ApiResult.Error(Exception("something went wrong")))
    }
}