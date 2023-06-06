package com.themovieguide.data.sources.local.repository.details

import com.themovieguide.data.sources.local.database.AppDatabase
import com.themovieguide.data.sources.local.model.DetailsTvDB
import com.themovieguide.data.utils.EMPTY
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsDBImpl @Inject constructor(private val app: AppDatabase) : DetailsDBRepository {
    override fun insert(tv: DetailsTvDB) {
        app.detailsDao().insert(tv)
    }

    override fun insertTelevision(tv: DetailsTvDB) {
        app.detailsDao().insertTelevision(
            adult = tv.adult ?: false,
            backdropPath = tv.backdropPath ?: EMPTY,
            createdBy = tv.createdBy ?: EMPTY,
            episodeRunTime = tv.episodeRunTime ?: EMPTY,
            firstAirDate = tv.firstAirDate ?: EMPTY,
            genres = tv.genres ?: EMPTY,
            homepage = tv.homepage ?: EMPTY,
            id = tv.id ?: 0,
            inProduction = tv.inProduction ?: false,
            languages = tv.languages ?: EMPTY,
            lastAirDate = tv.lastAirDate ?: EMPTY,
            lastEpisodeToAir = tv.lastEpisodeToAir ?: EMPTY,
            name = tv.name ?: EMPTY,
            networks = tv.networks ?: EMPTY,
            nextEpisodeToAir = tv.nextEpisodeToAir ?: EMPTY,
            numberOfEpisodes = tv.numberOfEpisodes ?: 0,
            numberOfSeasons = tv.numberOfSeasons ?: 0,
            originCountry = tv.originCountry ?: EMPTY,
            originalLanguage = tv.originalLanguage ?: EMPTY,
            originalName = tv.originalName ?: EMPTY,
            overview = tv.overview ?: EMPTY,
            popularity = tv.popularity ?: 0.0,
            posterPath = tv.posterPath ?: EMPTY,
            productionCompanies = tv.productionCompanies ?: EMPTY,
            productionCountries = tv.productionCountries ?: EMPTY,
            seasons = tv.seasons ?: EMPTY,
            spokenLanguages = tv.spokenLanguages ?: EMPTY,
            status = tv.status ?: EMPTY,
            tagline = tv.tagline ?: EMPTY,
            type = tv.type ?: EMPTY,
            voteAverage = tv.voteAverage ?: 0.0,
            voteCount = tv.voteCount ?: 0,
        )
    }

    override fun getTelevision(): Flow<List<DetailsTvDB>> {
        return app.detailsDao().getTelevision()
    }

    override fun getTelevisionByTitle(): Flow<List<DetailsTvDB>> {
        return app.detailsDao().getTelevision()
    }

    override suspend fun getTelevisionById(tvId: Int): DetailsTvDB {
        return app.detailsDao().getTelevisionById(tvId)
    }

    override suspend fun deleteTelevision(tvId: Int) {
        app.detailsDao().deleteTelevision(tvId)
    }

    override suspend fun delete() {
        app.detailsDao().delete()
    }
}
