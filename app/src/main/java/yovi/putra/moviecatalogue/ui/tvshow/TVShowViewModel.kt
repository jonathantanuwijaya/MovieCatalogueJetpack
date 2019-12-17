package yovi.putra.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import yovi.putra.moviecatalogue.data.entity.TVShow
import yovi.putra.moviecatalogue.data.repository.TVShowRepository

class TVShowViewModel : ViewModel() {
    fun getTvShow() : MutableList<TVShow> {
        return TVShowRepository.getTVShow()
    }

    fun getTvShow(id: Int) : TVShow? = TVShowRepository.getTVShowById(id)
}