package com.rob729.movies.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkHelper(private val context: Context) {

    private val connectivityManager: ConnectivityManager? by lazy { context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager }

    fun isConnectedToInternet(): Boolean {
        val networkCapabilities: NetworkCapabilities? =
            connectivityManager?.getNetworkCapabilities(connectivityManager?.activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}