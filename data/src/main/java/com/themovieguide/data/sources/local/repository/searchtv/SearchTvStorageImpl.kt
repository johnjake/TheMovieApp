package com.themovieguide.data.sources.local.repository.searchtv

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.SearchTvDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchTvStorageImpl @Inject constructor(
    private var app: AppDatabase,
) : SearchTvStorageRepository {
    override fun insert(tv: SearchTvDB) {
        app.searchTvDao().insert(tv)
    }

    override fun insertTelevision(tv: SearchTvDB) {
        app.searchTvDao().insertSearchTelevision(
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

    override fun getTelevision(query: String): Flow<List<SearchTvDB>> {
        return app.searchTvDao().getTelevision(name = query)
    }

    override fun getTelevisionByTitle(): Flow<List<SearchTvDB>> {
        return app.searchTvDao().getTelevisionByTitle()
    }

    override suspend fun getTelevisionById(tvId: Int): SearchTvDB {
        return app.searchTvDao().getTelevisionById(tvId)
    }

    override suspend fun deleteTelevision(tvId: Int) {
        app.searchTvDao().deleteTelevision(tvId)
    }

    override suspend fun delete() {
        app.searchTvDao().delete()
    }
}
