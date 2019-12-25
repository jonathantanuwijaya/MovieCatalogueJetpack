package yovi.putra.moviecatalogue.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yovi.putra.moviecatalogue.ui.movie.list.MovieFm
import yovi.putra.moviecatalogue.ui.tvshow.TVShowFm

class DashboardViewModel : ViewModel() {
    private var listFragment: MutableLiveData<ArrayList<Fragment>>? = null

    fun getFragment() : MutableLiveData<ArrayList<Fragment>>? {
        if (listFragment == null) {
            listFragment = MutableLiveData()
            val arrFm = arrayListOf<Fragment>(
                MovieFm(), TVShowFm()
            )
            listFragment?.postValue(arrFm)
        }
        return listFragment
    }
}