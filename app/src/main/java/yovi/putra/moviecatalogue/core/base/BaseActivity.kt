package yovi.putra.moviecatalogue.core.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import yovi.putra.moviecatalogue.core.utils.ui.LoadingController

/**
 * Created by yovi.putra
 * on 09/Mar/2019 10:56
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    private val loading: LoadingController by lazy {
        LoadingController(this)
    }

    override val contextView: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setupLayoutId())
        setupData(savedInstanceState)
        setupUI(savedInstanceState)
    }

    abstract fun setupLayoutId() : Int

    abstract fun setupData(savedInstanceState: Bundle?)

    abstract fun setupUI(savedInstanceState: Bundle?)

    override fun onShowLoader() {
        loading.show()
    }

    override fun onHideLoader() {
        loading.hide()
    }
}