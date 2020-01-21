package yovi.putra.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

class TVShowFavoriteViewModel(private val tvShowRepository: TVShowRepository) : BaseViewModel() {
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
        tvShowRepository.getTVShowList()
            .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { tvShow?.postValue(ResultState.Success(it)) },
                { tvShow?.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }
}