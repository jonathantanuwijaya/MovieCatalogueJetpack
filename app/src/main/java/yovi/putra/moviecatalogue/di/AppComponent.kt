package yovi.putra.moviecatalogue.di

import org.koin.core.module.Module
import org.koin.dsl.module
import yovi.putra.moviecatalogue.core.services.RetrofitService
import yovi.putra.moviecatalogue.data.remote.MovieApi
import yovi.putra.moviecatalogue.data.remote.TVShowApi
import yovi.putra.moviecatalogue.data.repository.MovieRepository
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

val networkModule = module {
    single { RetrofitService.api<MovieApi>() }
    single { RetrofitService.api<TVShowApi>() }
}

val dataSourceModule = module {
    single { MovieRepository(get()) }
    single { TVShowRepository(get()) }
}

val viewModelModule = module {

}

val appModules: List<Module> = listOf(dataSourceModule, networkModule, viewModelModule)