package yovi.putra.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import yovi.putra.moviecatalogue.data.entity.TVShowItem

@Dao
interface TVShowDao {
    @Query("SELECT * from tvshow_table ORDER BY id ASC")
    fun getTVShow(): DataSource.Factory<Int, TVShowItem>

    @Query("SELECT * from tvshow_table WHERE id = :idMovie")
    fun getTVShow(idMovie: Int): LiveData<TVShowItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: TVShowItem)

    @Query("DELETE FROM tvshow_table WHERE id = :idMovie")
    suspend fun delete(idMovie: Int)
}