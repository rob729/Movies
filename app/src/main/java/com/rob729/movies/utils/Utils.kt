package com.rob729.movies.utils

import android.content.Context
import coil.request.CachePolicy
import coil.request.ImageRequest

object Utils {

    fun getImageRequestModel(context: Context, imageUrl: String, crossFadeDuration: Int = 200) =
        ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .crossfade(crossFadeDuration)
            .networkCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
}