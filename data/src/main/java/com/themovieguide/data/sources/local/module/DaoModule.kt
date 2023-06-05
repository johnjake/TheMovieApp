package com.themovieguide.data.sources.local.module

import android.content.Context
import com.themovieguide.data.sources.local.dao.MovieDao
import com.themovieguide.data.sources.local.dao.RatedTvDao
import com.themovieguide.data.sources.local.dao.SearchDao
import com.themovieguide.data.sources.local.dao.SearchTvDao
import com.themovieguide.data.sources.local.dao.TheaterDao
import com.themovieguide.data.sources.local.dao.TopRatedDao
import com.themovieguide.data.sources.local.dao.UpcomingDao
import com.themovieguide.data.sources.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext ctx: Context) =
        AppDatabase.getInstance(ctx)

    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao = db.movieDao()

    @Provides
    fun provideTheaterDao(db: AppDatabase): TheaterDao = db.theaterDao()

    @Provides
    fun provideTopRatedDao(db: AppDatabase): TopRatedDao = db.topRatedDao()

    @Provides
    fun provideSearchDao(db: AppDatabase): SearchDao = db.searchDao()

    @Provides
    fun provideUpcomingDao(db: AppDatabase): UpcomingDao = db.upcomingDao()

    @Provides
    fun provideRatedTvDao(db: AppDatabase): RatedTvDao = db.ratedTvDao()

    @Provides
    fun provideSearchTvDao(db: AppDatabase): SearchTvDao = db.searchTvDao()
}
