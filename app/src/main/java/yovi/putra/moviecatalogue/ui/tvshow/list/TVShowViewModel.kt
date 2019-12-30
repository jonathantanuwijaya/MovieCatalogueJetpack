package yovi.putra.moviecatalogue.ui.tvshow.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

class TVShowViewModel(private val tvShowRepository: TVShowRepository) : BaseViewModel() {
    private var tvShow: MutableLiveData<ResultState>? = null

    fun getTVShow(): LiveData<ResultState>? {
        tvShow?.let {
            loaderState.postValue(LoaderState.Hide)
        } ?: run {
            setTVShow()
        }
        return tvShow
    }

    fun setTVShow() {
        tvShow ?: run { tvShow = MutableLiveData() }
        loaderState.postValue(LoaderState.Show)
        tvShowRepository.getTVShowList()
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { tvShow?.postValue(ResultState.Success(it)) },
                { tvShow?.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }
}