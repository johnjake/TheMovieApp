package com.themovieguide.data.sources.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.themovieguide.data.sources.local.dao.MovieDao
import com.themovieguide.data.sources.local.dao.RatedTvDao
import com.themovieguide.data.sources.local.dao.SearchDao
import com.themovieguide.data.sources.local.dao.TheaterDao
import com.themovieguide.data.sources.local.dao.TopRatedDao
import com.themovieguide.data.sources.local.dao.UpcomingDao
import com.themovieguide.data.sources.local.model.MovieDB
import com.themovieguide.data.sources.local.model.RatedTvDB
import com.themovieguide.data.sources.local.model.SearchDB
import com.themovieguide.data.sources.local.model.TheaterDB
import com.themovieguide.data.sources.local.model.TopRatedDB
import com.themovieguide.data.sources.local.model.UpcomingDB
import com.themovieguide.data.utils.MARKETING_DB

@Database(
    entities = [
        MovieDB::class,
        TheaterDB::class,
        TopRatedDB::class,
        SearchDB::class,
        UpcomingDB::class,
        RatedTvDB::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun theaterDao(): TheaterDao
    abstract fun topRatedDao(): TopRatedDao
    abstract fun searchDao(): SearchDao
    abstract fun upcomingDao(): UpcomingDao
    abstract fun ratedTvDao(): RatedTvDao
    companion object {
        fun getInstance(context: Context): AppDatabase = buildDatabase(context)
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                MARKETING_DB,
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
