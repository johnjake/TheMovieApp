package com.themovieguide.data.sources.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.themovieguide.data.sources.local.dao.DetailsDao
import com.themovieguide.data.sources.local.dao.DiscoverDao
import com.themovieguide.data.sources.local.dao.MovieDao
import com.themovieguide.data.sources.local.dao.RatedTvDao
import com.themovieguide.data.sources.local.dao.SearchDao
import com.themovieguide.data.sources.local.dao.SearchTvDao
import com.themovieguide.data.sources.local.dao.TheaterDao
import com.themovieguide.data.sources.local.dao.TodayAirDao
import com.themovieguide.data.sources.local.dao.TopRatedDao
import com.themovieguide.data.sources.local.dao.TrendingDao
import com.themovieguide.data.sources.local.dao.UpcomingDao
import com.themovieguide.data.sources.local.model.DetailsTvDB
import com.themovieguide.data.sources.local.model.DiscoverDB
import com.themovieguide.data.sources.local.model.MovieDB
import com.themovieguide.data.sources.local.model.RatedTvDB
import com.themovieguide.data.sources.local.model.SearchDB
import com.themovieguide.data.sources.local.model.SearchTvDB
import com.themovieguide.data.sources.local.model.TheaterDB
import com.themovieguide.data.sources.local.model.TodayAirDB
import com.themovieguide.data.sources.local.model.TopRatedDB
import com.themovieguide.data.sources.local.model.TrendingDB
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
        SearchTvDB::class,
        TodayAirDB::class,
        DiscoverDB::class,
        TrendingDB::class,
        DetailsTvDB::class,
    ],
    version = 10,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun theaterDao(): TheaterDao
    abstract fun topRatedDao(): TopRatedDao
    abstract fun searchDao(): SearchDao
    abstract fun upcomingDao(): UpcomingDao
    abstract fun ratedTvDao(): RatedTvDao
    abstract fun searchTvDao(): SearchTvDao
    abstract fun todayAirDao(): TodayAirDao
    abstract fun discoverDao(): DiscoverDao
    abstract fun trendingDao(): TrendingDao
    abstract fun detailsDao(): DetailsDao
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
