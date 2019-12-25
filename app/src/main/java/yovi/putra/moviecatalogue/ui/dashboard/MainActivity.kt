package yovi.putra.moviecatalogue.ui.dashboard

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseActivity
import yovi.putra.moviecatalogue.core.utils.ui.fragmentReplace

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var menuVM : DashboardViewModel
    private var frag = arrayListOf<Fragment>()

    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData() {
        menuVM = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }

    override fun setupUI() {
        nav_view.setOnNavigationItemSelectedListener(this)
        menuVM.getFragment()?.observe(this, movieFm)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_movie -> fragmentReplace(R.id.container, frag[0])
            R.id.action_tvshow -> fragmentReplace(R.id.container, frag[1])
        }
        return true
    }

    private var movieFm = Observer<ArrayList<Fragment>> {
        frag = it
    }
}
