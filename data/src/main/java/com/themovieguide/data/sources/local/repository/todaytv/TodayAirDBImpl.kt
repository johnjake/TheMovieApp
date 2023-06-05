package com.themovieguide.data.sources.local.repository.todaytv

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.TodayAirDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodayAirDBImpl @Inject constructor(
    private var app: AppDatabase,
) : TodayAirDBRepository {
    override fun insert(tv: TodayAirDB) {
        app.todayAirDao().insert(tv)
    }

    override fun insertTelevision(tv: TodayAirDB) {
        app.todayAirDao().insertTelevision(
            backdropPath = tv.backdropPath ?: EMPTY,
            firstAirDate = tv.firstAirDate ?: EMPTY,
            genreIds = tv.genreIds ?: EMPTY,
            id = tv.id ?: 0,
            name = tv.name ?: EMPTY,
            originCountry = tv.originCountry ?: EMPTY,
            originalLanguage = tv.originalLanguage ?: EMPTY,
            originalName = tv.originalName ?: EMPTY,
            overview = tv.overview ?: EMPTY,
            popularity = tv.popularity ?: 0.0,
            posterPath = tv.posterPath ?: EMPTY,
            voteAverage = tv.voteAverage ?: 0.0,
            voteCount = tv.voteCount ?: 0,
        )
    }

    override fun getTelevision(): Flow<List<TodayAirDB>> {
        return app.todayAirDao().getTelevision()
    }

    override fun getTelevisionByTitle(): Flow<List<TodayAirDB>> {
        return app.todayAirDao().getTelevisionByTitle()
    }

    override suspend fun getTelevisionById(tvId: Int): TodayAirDB {
        return app.todayAirDao().getTelevisionById(tvId)
    }

    override suspend fun deleteTelevision(tvId: Int) {
        app.ratedTvDao().deleteTelevision(tvId)
    }

    override suspend fun delete() {
        app.ratedTvDao().delete()
    }
}
