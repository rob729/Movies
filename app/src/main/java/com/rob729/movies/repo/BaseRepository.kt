package com.rob729.movies.repo

import com.rob729.movies.utils.Constants
import retrofit2.Response

abstract class BaseRepository {

    sealed class ApiResult<out T> {
        data class Success<out T>(val data: T) : ApiResult<T>()
        data class Error(val message: String) : ApiResult<Nothing>()
    }

    suspend fun <T> handleResponse(apiCall: suspend () -> Response<T>): ApiResult<T> {
        return runCatching {
            val response  = apiCall.invoke()
            response.body().takeIf { response.isSuccessful }?.let {
                ApiResult.Success<T>(it)
            } ?: ApiResult.Error(response.message())
        }.onFailure {
            ApiResult.Error(it.message ?: Constants.DEFAULT_ERROR_MESSAGE)
        }.getOrDefault(ApiResult.Error(Constants.DEFAULT_ERROR_MESSAGE))
    }
}