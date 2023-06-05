package com.themovieguide.data.sources.local.repository.ratedtv

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.RatedTvDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RatedTelevisionDBImpl @Inject constructor(
    private var app: AppDatabase,
) : RatedTelevisionDBRepository {
    override fun insert(tv: RatedTvDB) {
        app.ratedTvDao().insert(tv)
    }

    override fun insertTelevision(tv: RatedTvDB) {
        app.ratedTvDao().insertTelevision(
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

    override fun getTelevision(): Flow<List<RatedTvDB>> {
        return app.ratedTvDao().getTelevision()
    }

    override fun getTelevisionByTitle(): Flow<List<RatedTvDB>> {
        return app.ratedTvDao().getTelevisionByTitle()
    }

    override suspend fun getTelevisionById(tvId: Int): RatedTvDB {
        return app.ratedTvDao().getTelevisionById(tvId)
    }

    override suspend fun deleteTelevision(tvId: Int) {
        app.ratedTvDao().deleteTelevision(tvId)
    }

    override suspend fun delete() {
        app.ratedTvDao().delete()
    }
}
