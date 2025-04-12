package com.rob729.movies.di

import com.rob729.movies.utils.NetworkHelper
import com.rob729.movies.ui.feature.details.DetailsViewModel
import com.rob729.movies.ui.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailsViewModel)
    singleOf(::NetworkHelper)
}