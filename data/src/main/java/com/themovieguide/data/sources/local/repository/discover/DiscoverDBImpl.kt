package com.themovieguide.data.sources.local.repository.discover

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.DiscoverDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverDBImpl @Inject constructor(
    private var app: AppDatabase,
) : DiscoverDBRepository {
    override fun insert(tv: DiscoverDB) {
        app.discoverDao().insert(tv)
    }

    override fun insertTelevision(tv: DiscoverDB) {
        app.discoverDao().insertTelevision(
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

    override fun getTelevision(): Flow<List<DiscoverDB>> {
        return app.discoverDao().getTelevision()
    }

    override fun getTelevisionByTitle(): Flow<List<DiscoverDB>> {
        return app.discoverDao().getTelevisionByTitle()
    }

    override suspend fun getTelevisionById(tvId: Int): DiscoverDB {
        return app.discoverDao().getTelevisionById(tvId)
    }

    override suspend fun deleteTelevision(tvId: Int) {
        app.discoverDao().deleteTelevision(tvId)
    }

    override suspend fun delete() {
        app.discoverDao().delete()
    }
}
