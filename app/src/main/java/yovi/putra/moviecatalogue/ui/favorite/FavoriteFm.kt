package yovi.putra.moviecatalogue.ui.favorite

import android.view.View
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.adapter.VPagerAdapter
import yovi.putra.moviecatalogue.core.base.BaseFragment

class FavoriteFm : BaseFragment() {
    private val menuVM : FavoriteViewModel by viewModel()
    private lateinit var vPagerAdapter: VPagerAdapter

    override fun setupLayoutId(): Int = R.layout.fragment_favorite

    override fun setupData() {
        vPagerAdapter = VPagerAdapter(childFragmentManager)
        vPagerAdapter.setItem(menuVM.pagers)
    }

    override fun setupUI() {
        viewpager.apply {
            adapter = vPagerAdapter
            overScrollMode = View.OVER_SCROLL_NEVER
        }
    }
}