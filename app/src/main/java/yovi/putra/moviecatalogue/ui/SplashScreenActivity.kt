package yovi.putra.moviecatalogue.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import yovi.putra.moviecatalogue.R
import yovi.putra.moviecatalogue.core.base.BaseActivity
import yovi.putra.moviecatalogue.ui.dashboard.DashboardActivity

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : BaseActivity() {

    override fun setupLayoutId(): Int = R.layout.activity_splash_screen

    override fun setupData(savedInstanceState: Bundle?) {
        Handler().postDelayed({
            startActivity(Intent(this, DashboardActivity::class.java))
        }, 3000)
    }

    override fun setupUI(savedInstanceState: Bundle?) {}
}
