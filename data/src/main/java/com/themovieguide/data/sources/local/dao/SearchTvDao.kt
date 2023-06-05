package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.SearchTvDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SearchTvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(tv: SearchTvDB)

    @Query(
        "insert into search_tv(backdropPath, firstAirDate, genreIds, id, name, originCountry, originalLanguage, originalName, overview, popularity, posterPath, voteAverage, voteCount) " +
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
            "where not exists (select id From search_tv where id = :id)",
    )
    abstract fun insertSearchTelevision(
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

    @Query("select * from search_tv where id = :tvId")
    abstract suspend fun getTelevisionById(tvId: Int): SearchTvDB

    @Query("select * from search_tv where name LIKE :name || '%'")
    abstract fun getTelevision(name: String): Flow<List<SearchTvDB>>

    @Query("select * from search_tv group by originalName")
    abstract fun getTelevisionByTitle(): Flow<List<SearchTvDB>>

    @Query("DELETE FROM search_tv where id = :tvId")
    abstract suspend fun deleteTelevision(tvId: Int)

    @Query("DELETE FROM search_tv")
    abstract suspend fun delete()
}
