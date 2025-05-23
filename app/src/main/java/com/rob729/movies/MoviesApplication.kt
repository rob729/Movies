package com.rob729.movies

import android.app.Application
import com.rob729.movies.di.apiModule
import com.rob729.movies.di.databaseModule
import com.rob729.movies.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(apiModule)
            modules(mainModule)
            modules(databaseModule)
        }
    }
}