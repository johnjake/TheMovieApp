package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.DetailsTvDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(tv: DetailsTvDB)

    @Query(
        "insert into details_tv(adult,backdropPath,createdBy,episodeRunTime,firstAirDate,genres,homepage,id,inProduction,languages,lastAirDate,lastEpisodeToAir,name,networks,nextEpisodeToAir,numberOfEpisodes,numberOfSeasons,originCountry,originalLanguage,originalName,overview,popularity,posterPath,productionCompanies,productionCountries,seasons,spokenLanguages,status,tagline,type,voteAverage,voteCount) " +
            "select" +
            " :adult, " +
            " :backdropPath, " +
            " :createdBy, " +
            " :episodeRunTime, " +
            " :firstAirDate, " +
            " :genres, " +
            " :homepage, " +
            " :id, " +
            " :inProduction, " +
            " :languages, " +
            " :lastAirDate, " +
            " :lastEpisodeToAir, " +
            " :name, " +
            " :networks, " +
            " :nextEpisodeToAir, " +
            " :numberOfEpisodes, " +
            " :numberOfSeasons, " +
            " :originCountry, " +
            " :originalLanguage, " +
            " :originalName, " +
            " :overview, " +
            " :popularity, " +
            " :posterPath, " +
            " :productionCompanies, " +
            " :productionCountries, " +
            " :seasons, " +
            " :spokenLanguages, " +
            " :status, " +
            " :tagline, " +
            " :type, " +
            " :voteAverage, " +
            " :voteCount " +
            "where not exists (select id From details_tv where id = :id)",
    )
    abstract fun insertTelevision(
        adult: Boolean,
        backdropPath: String,
        createdBy: String,
        episodeRunTime: String,
        firstAirDate: String,
        genres: String,
        homepage: String,
        id: Int,
        inProduction: Boolean,
        languages: String,
        lastAirDate: String,
        lastEpisodeToAir: String,
        name: String,
        networks: String,
        nextEpisodeToAir: String,
        numberOfEpisodes: Int,
        numberOfSeasons: Int,
        originCountry: String,
        originalLanguage: String,
        originalName: String,
        overview: String,
        popularity: Double,
        posterPath: String,
        productionCompanies: String,
        productionCountries: String,
        seasons: String,
        spokenLanguages: String,
        status: String,
        tagline: String,
        type: String,
        voteAverage: Double,
        voteCount: Int,
    )

    @Query("select * from details_tv where id = :tvId")
    abstract suspend fun getTelevisionById(tvId: Int): DetailsTvDB

    @Query("select * from details_tv group by dbId")
    abstract fun getTelevision(): Flow<List<DetailsTvDB>>

    @Query("select * from details_tv group by originalName")
    abstract fun getTelevisionByTitle(): Flow<List<DetailsTvDB>>

    @Query("DELETE FROM details_tv where id = :tvId")
    abstract suspend fun deleteTelevision(tvId: Int)

    @Query("DELETE FROM details_tv")
    abstract suspend fun delete()
}
