package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.RatedTvDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RatedTvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(movie: RatedTvDB)

    @Query(
        "insert into rated_tv(backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount) " +
            "select" +
            " :backdropPath, " +
            " :firstAirDate, " +
            " :genreIds, " +
            " :id, " +
            " :name, " +
            " :originCountry, " +
            " :originalLanguage, " +
            " :originalName, " +
            " :overview,  " +
            " :popularity,  " +
            " :posterPath,  " +
            " :voteAverage, " +
            " :voteCount " +
            "where not exists (select id From rated_tv where id = :id)",
    )
    abstract fun insertTelevision(
        backdropPath: String,
        firstAirDate: String,
        genreIds: String,
        id: Int,
        name: String,
        originCountry: String,
        originalLanguage: String,
        originalName: String,
        overview: String,
        popularity: Double,
        posterPath: String,
        voteAverage: Double,
        voteCount: Int,
    )

    @Query("select * from rated_tv where id = :tvId")
    abstract suspend fun getTelevisionById(tvId: Int): RatedTvDB

    @Query("select * from rated_tv group by dbId")
    abstract fun getTelevision(): Flow<List<RatedTvDB>>

    @Query("select * from rated_tv group by originalName")
    abstract fun getTelevisionByTitle(): Flow<List<RatedTvDB>>

    @Query("DELETE FROM rated_tv where id = :tvId")
    abstract suspend fun deleteTelevision(tvId: Int)

    @Query("DELETE FROM rated_tv")
    abstract suspend fun delete()
}
