package yovi.putra.moviecatalogue.view

import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.base.BaseActivity

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData() {}

    override fun setupUI() {
        nav_view.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_movie -> return true
            R.id.action_tvshow -> return true
        }
        return true
    }
}
