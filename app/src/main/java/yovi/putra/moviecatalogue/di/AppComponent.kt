package yovi.putra.moviecatalogue.di

import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import yovi.putra.moviecatalogue.core.services.RetrofitService
import yovi.putra.moviecatalogue.data.local.MovieDatabase
import yovi.putra.moviecatalogue.data.remote.MovieApi
import yovi.putra.moviecatalogue.data.remote.TVShowApi
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.data.repository.TVShowRepository
import yovi.putra.moviecatalogue.ui.dashboard.DashboardViewModel
import yovi.putra.moviecatalogue.ui.favorite.FavoriteViewModel
import yovi.putra.moviecatalogue.ui.favorite.movie.MovieFavoriteViewModel
import yovi.putra.moviecatalogue.ui.favorite.tvshow.TVShowFavoriteViewModel
import yovi.putra.moviecatalogue.ui.movie.detail.DetailMovieViewModel
import yovi.putra.moviecatalogue.ui.movie.list.MovieFmViewModel
import yovi.putra.moviecatalogue.ui.tvshow.detail.DetailTVShowViewModel
import yovi.putra.moviecatalogue.ui.tvshow.list.TVShowFmViewModel

val networkModule = module {
    single { RetrofitService.api<MovieApi>() }
    single { RetrofitService.api<TVShowApi>() }

    single { MovieDatabase.getDatabase(androidApplication()).movieDao() }
    single { MovieDatabase.getDatabase(androidApplication()).tvShowDao() }
}

val dataSourceModule = module {
    single { MovieRepository(get(), get()) }
    single { TVShowRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { DashboardViewModel() }

    viewModel { MovieFmViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }

    viewModel { TVShowFmViewModel(get()) }
    viewModel { DetailTVShowViewModel(get()) }

    viewModel { FavoriteViewModel() }
    viewModel { MovieFavoriteViewModel(get()) }
    viewModel { TVShowFavoriteViewModel(get()) }
}

val appModules: List<Module> = listOf(dataSourceModule, networkModule, viewModelModule)