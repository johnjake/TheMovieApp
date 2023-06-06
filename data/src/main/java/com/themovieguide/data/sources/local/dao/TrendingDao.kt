package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.TrendingDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TrendingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(tv: TrendingDB)

    @Query(
        "insert into trending_tv(backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount) " +
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
            "where not exists (select id From trending_tv where id = :id)",
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

    @Query("select * from trending_tv where id = :tvId")
    abstract suspend fun getTelevisionById(tvId: Int): TrendingDB

    @Query("select * from trending_tv group by dbId")
    abstract fun getTelevision(): Flow<List<TrendingDB>>

    @Query("select * from trending_tv group by originalName")
    abstract fun getTelevisionByTitle(): Flow<List<TrendingDB>>

    @Query("DELETE FROM trending_tv where id = :tvId")
    abstract suspend fun deleteTelevision(tvId: Int)

    @Query("DELETE FROM trending_tv")
    abstract suspend fun delete()
}
