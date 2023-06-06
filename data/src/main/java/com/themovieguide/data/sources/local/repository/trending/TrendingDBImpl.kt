package com.themovieguide.data.sources.local.repository.trending

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.TrendingDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingDBImpl @Inject constructor(
    private var app: AppDatabase,
) : TrendingDBRepository {
    override fun insert(tv: TrendingDB) {
        app.trendingDao().insert(tv)
    }

    override fun insertTelevision(tv: TrendingDB) {
        app.trendingDao().insertTelevision(
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

    override fun getTelevision(): Flow<List<TrendingDB>> {
        return app.trendingDao().getTelevision()
    }

    override fun getTelevisionByTitle(): Flow<List<TrendingDB>> {
        return app.trendingDao().getTelevisionByTitle()
    }

    override suspend fun getTelevisionById(tvId: Int): TrendingDB {
        return app.trendingDao().getTelevisionById(tvId)
    }

    override suspend fun deleteTelevision(tvId: Int) {
        app.trendingDao().deleteTelevision(tvId)
    }

    override suspend fun delete() {
        app.trendingDao().delete()
    }
}