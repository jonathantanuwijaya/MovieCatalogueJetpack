package yovi.putra.moviecatalogue.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import yovi.putra.moviecatalogue.core.utils.state.LoaderState


open class BaseViewModel : ViewModel() {
    protected var subscriber = CompositeDisposable()

    protected var loaderState = MutableLiveData<LoaderState>()
    val loader : LiveData<LoaderState>
        get() = loaderState

    override fun onCleared() {
        super.onCleared()
        subscriber.dispose()
    }
}