package yovi.putra.moviecatalogue.ui.tvshow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

class TVShowViewModel(private val tvShowRepository: TVShowRepository) : BaseViewModel() {
    private var tvShowResult = MutableLiveData<ResultState>()
    val tvShow: LiveData<ResultState>
        get() = tvShowResult

    init {
        reloadTVShow()
    }

    fun reloadTVShow() {
        loaderState.postValue(LoaderState.Show)
        tvShowRepository.getTVShowList()
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { tvShowResult.postValue(ResultState.Success(it)) },
                { tvShowResult.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }
}