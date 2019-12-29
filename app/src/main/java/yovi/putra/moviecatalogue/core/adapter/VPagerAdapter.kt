package yovi.putra.moviecatalogue.core.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by yovi.putra
 * on 03/Mar/2019 17:22
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
class VPagerAdapter(
        fm: FragmentManager
) : FragmentPagerAdapter(fm) {
    private var pagers = mutableListOf<VPager>()

    fun setItem(pagers: List<VPager>) {
        this.pagers.clear()
        this.pagers.addAll(pagers)
        this.notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment = pagers[position].fragment

    override fun getCount(): Int = pagers.size

    override fun getPageTitle(position: Int): CharSequence? = pagers[position].title
}

class VPager(val title: String, val fragment: Fragment)