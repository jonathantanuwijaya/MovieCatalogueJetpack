package yovi.putra.moviecatalogue.core.base

import android.content.Context

/**
 * Created by yovi.putra
 * on 09/Mar/2019 10:55
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface IBaseView {

    val contextView: Context

    fun onShowLoader()

    fun onHideLoader()
}
