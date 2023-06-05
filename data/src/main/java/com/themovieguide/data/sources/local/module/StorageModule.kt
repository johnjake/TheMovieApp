package com.themovieguide.data.sources.local.module

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.repository.ratedtv.RatedTelevisionDBImpl
import com.themovieguide.data.sources.local.repository.ratedtv.RatedTelevisionDBRepository
import com.themovieguide.data.sources.local.repository.search.SearchDBImpl
import com.themovieguide.data.sources.local.repository.search.SearchDBRepository
import com.themovieguide.data.sources.local.repository.searchtv.SearchTvStorageImpl
import com.themovieguide.data.sources.local.repository.searchtv.SearchTvStorageRepository
import com.themovieguide.data.sources.local.repository.theater.InTheaterDBRepository
import com.themovieguide.data.sources.local.repository.theater.TheaterDBImpl
import com.themovieguide.data.sources.local.repository.todaytv.TodayAirDBImpl
import com.themovieguide.data.sources.local.repository.todaytv.TodayAirDBRepository
import com.themovieguide.data.sources.local.repository.toprated.TopRatedDBImpl
import com.themovieguide.data.sources.local.repository.toprated.TopRatedDBRepository
import com.themovieguide.data.sources.local.repository.upcoming.UpcomingDBImpl
import com.themovieguide.data.sources.local.repository.upcoming.UpcomingDBRepository
import com.themovieguide.data.sources.local.repository.visited.DBMovieImpl
import com.themovieguide.data.sources.local.repository.visited.DBMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    @Provides
    fun providesLocalMovieRepository(storage: AppDatabase): DBMovieRepository = DBMovieImpl(app = storage)

    @Provides
    fun provideLocalInTheaterRepository(storage: AppDatabase): InTheaterDBRepository = TheaterDBImpl(app = storage)

    @Provides
    fun provideLocalTopRatedRepository(storage: AppDatabase): TopRatedDBRepository = TopRatedDBImpl(app = storage)

    @Provides
    fun provideStorageSearchRepository(storage: AppDatabase): SearchDBRepository = SearchDBImpl(app = storage)

    @Provides
    fun provideStorageUpcomingRepository(storage: AppDatabase): UpcomingDBRepository = UpcomingDBImpl(app = storage)

    @Provides
    fun provideStorageRatedTelevisionRepository(storage: AppDatabase): RatedTelevisionDBRepository = RatedTelevisionDBImpl(app = storage)

    @Provides
    fun provideStorageSearchTelevisionRepository(storage: AppDatabase): SearchTvStorageRepository = SearchTvStorageImpl(app = storage)

    @Provides
    fun provideStorageTodayAir(storage: AppDatabase): TodayAirDBRepository = TodayAirDBImpl(app = storage)
}
