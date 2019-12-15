package yovi.putra.moviecatalogue.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import yovi.putra.moviecatalogue.R


/**
 * Created by yovi.putra
 * on 10/Mar/2019 11:23
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */

abstract class BaseFragment : Fragment(), IBaseView {

    private var activity: IBaseView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as IBaseView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(setupLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupData()
        setupUI()
    }

    abstract fun setupLayoutId() : Int

    abstract fun setupData()

    abstract fun setupUI()

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override val contextView: Context
        get() = context as Context

    override fun onShowLoader() {}

    override fun onHideLoader() {}
}