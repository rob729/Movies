package com.rob729.movies.models

interface INetworkCurator<out T> {

    fun transformToCuratedData(): T
}