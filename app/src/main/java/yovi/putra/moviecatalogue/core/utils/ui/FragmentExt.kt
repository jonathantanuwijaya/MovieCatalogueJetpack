package yovi.putra.moviecatalogue.core.utils.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


fun FragmentActivity.fragmentReplace(container: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(container, fragment, fragment::class.java.simpleName)
        .addToBackStack(null)
        .commit()
}