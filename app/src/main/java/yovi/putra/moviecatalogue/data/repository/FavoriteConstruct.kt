package yovi.putra.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

interface FavoriteConstruct<DATA> {
    suspend fun addFavorite(data: DATA)

    suspend fun deleteFavorite(id: Int)

    fun getFavoriteById(id: Int) : LiveData<DATA>?

    fun getFavorite(page: Int) : LiveData<PagedList<DATA>>
}