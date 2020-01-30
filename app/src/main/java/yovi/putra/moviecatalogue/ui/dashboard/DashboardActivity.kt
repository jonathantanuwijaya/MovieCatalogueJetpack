package yovi.putra.moviecatalogue.ui.dashboard

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.adapter.VPagerAdapter
import yovi.putra.moviecatalogue.core.base.BaseActivity

class DashboardActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val menuVM : DashboardViewModel by viewModel()
    private lateinit var vPagerAdapter : VPagerAdapter

    override fun setupLayoutId(): Int = R.layout.activity_dashboard

    override fun setupData(savedInstanceState: Bundle?) {
        vPagerAdapter = VPagerAdapter(supportFragmentManager)
        vPagerAdapter.setItem(menuVM.pagers)
        nav_view.selectedItemId = R.id.action_movie
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        nav_view.setOnNavigationItemSelectedListener(this)
        setupViewPager()
    }

    private fun setupViewPager() {
        view_pager.apply {
            adapter = vPagerAdapter
            overScrollMode = View.OVER_SCROLL_NEVER
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {}

                override fun onPageSelected(position: Int) {
                    nav_view.menu.getItem(position).isChecked = true
                }
            })
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_movie -> view_pager.currentItem = 0
            R.id.action_tvshow -> view_pager.currentItem = 1
            R.id.action_favorite -> view_pager.currentItem = 2
        }
        return true
    }
}
