package yovi.putra.moviecatalogue.ui.tvshow.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.moviecatalogue.core.base.BaseViewModel
import yovi.putra.moviecatalogue.core.utils.state.LoaderState
import yovi.putra.moviecatalogue.core.utils.state.ResultState
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

class DetailTVShowViewModel(private val tvShowRepository: TVShowRepository) : BaseViewModel() {
    private var tvShowResult : MutableLiveData<ResultState>? = null

    fun getTVShow(tvShowId: Int): LiveData<ResultState>? {
        if (tvShowResult == null) {
            tvShowResult = MutableLiveData()

            loaderState.postValue(LoaderState.Show)
            tvShowRepository.getTVShowDetail(tvShowId)
                .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
                .subscribe(
                    { tvShowResult?.postValue(ResultState.Success(it)) },
                    { tvShowResult?.postValue(ResultState.Error(it)) }
                )
                .addTo(subscriber)
        }
        return tvShowResult
    }
}