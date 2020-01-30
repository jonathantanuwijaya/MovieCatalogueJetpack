package yovi.putra.moviecatalogue.core.utils.threat

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by yovi.putra
 *    on 16/Mar/2019 13:40
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
object RxUtils {
    fun <T> applyObservableAsync(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applyObservableCompute(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { observable ->
            observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applyObservableMainThread(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable -> observable.observeOn(AndroidSchedulers.mainThread()) }
    }
}