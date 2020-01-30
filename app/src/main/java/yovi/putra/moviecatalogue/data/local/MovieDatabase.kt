package yovi.putra.moviecatalogue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import yovi.putra.moviecatalogue.data.entity.MovieItem
import yovi.putra.moviecatalogue.data.entity.TVShowItem

@Database(entities = [MovieItem::class, TVShowItem::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TVShowDao

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_catalogue"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
