package yovi.putra.moviecatalogue.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import yovi.putra.moviecatalogue.core.services.RetrofitService
import yovi.putra.moviecatalogue.data.remote.MovieApi
import yovi.putra.moviecatalogue.data.remote.TVShowApi
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.data.repository.TVShowRepository
import yovi.putra.moviecatalogue.ui.movie.detail.DetailMovieViewModel
import yovi.putra.moviecatalogue.ui.movie.list.MovieFmViewModel
import yovi.putra.moviecatalogue.ui.tvshow.detail.DetailTVShowViewModel
import yovi.putra.moviecatalogue.ui.tvshow.list.TVShowViewModel

val networkModule = module {
    single { RetrofitService.api<MovieApi>() }
    single { RetrofitService.api<TVShowApi>() }
}

val dataSourceModule = module {
    single { MovieRepository(get()) }
    single { TVShowRepository(get()) }
}

val viewModelModule = module {
    viewModel { MovieFmViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { TVShowViewModel(get()) }
    viewModel { DetailTVShowViewModel(get()) }
}

val appModules: List<Module> = listOf(dataSourceModule, networkModule, viewModelModule)