package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.TodayAirDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TodayAirDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(movie: TodayAirDB)

    @Query(
        "insert into today_tv(backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount) " +
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
            "where not exists (select id From today_tv where id = :id)",
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

    @Query("select * from today_tv where id = :tvId")
    abstract suspend fun getTelevisionById(tvId: Int): TodayAirDB

    @Query("select * from today_tv group by dbId")
    abstract fun getTelevision(): Flow<List<TodayAirDB>>

    @Query("select * from today_tv group by originalName")
    abstract fun getTelevisionByTitle(): Flow<List<TodayAirDB>>

    @Query("DELETE FROM today_tv where id = :tvId")
    abstract suspend fun deleteTelevision(tvId: Int)

    @Query("DELETE FROM today_tv")
    abstract suspend fun delete()
}
