package yovi.putra.moviecatalogue.core.utils.state

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.reObserver(
    owner: LifecycleOwner,
    observer: Observer<T>
) {
    this.removeObserver(observer)
    this.observe(owner, observer)
}