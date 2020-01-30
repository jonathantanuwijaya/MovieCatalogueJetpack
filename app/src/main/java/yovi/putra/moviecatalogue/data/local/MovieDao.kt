package yovi.putra.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import yovi.putra.moviecatalogue.data.entity.MovieItem

@Dao
interface MovieDao {

    @Query("SELECT * from movie_table ORDER BY id ASC")
    fun getMovie(): DataSource.Factory<Int, MovieItem>

    @Query("SELECT * from movie_table WHERE id = :idMovie")
    fun getMovie(idMovie: Int): LiveData<MovieItem>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: MovieItem)

    @Query("DELETE FROM movie_table WHERE id = :idMovie")
    suspend fun delete(idMovie: Int)
}