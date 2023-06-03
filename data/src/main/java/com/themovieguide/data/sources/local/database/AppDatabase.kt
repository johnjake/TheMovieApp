package com.themovieguide.data.sources.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.themovieguide.data.sources.local.dao.MovieDao
import com.themovieguide.data.sources.local.model.MovieDB
import com.themovieguide.data.utils.MARKETING_DB

@Database(
    entities = [
        MovieDB::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
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
