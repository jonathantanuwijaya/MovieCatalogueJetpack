package yovi.putra.moviecatalogue.ui

import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.base.BaseActivity
import yovi.putra.moviecatalogue.ui.movie.MovieFm
import yovi.putra.moviecatalogue.ui.tvshow.TVShowFm
import yovi.putra.moviecatalogue.utils.fragmentReplace

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var movieFm : MovieFm
    private lateinit var tvShowFm: TVShowFm

    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData() {
        movieFm = MovieFm()
        tvShowFm = TVShowFm()
    }

    override fun setupUI() {
        nav_view.setOnNavigationItemSelectedListener(this)
        fragmentReplace(R.id.container, movieFm)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_movie -> fragmentReplace(R.id.container, movieFm)
            R.id.action_tvshow -> fragmentReplace(R.id.container, tvShowFm)
        }
        return true
    }
}
