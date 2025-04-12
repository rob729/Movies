package com.rob729.movies.di

import com.rob729.movies.database.MovieDao
import com.rob729.movies.database.MovieDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { MovieDatabase.getDatabase(get()) }
    single<MovieDao> { get<MovieDatabase>().movieDao() }
}
