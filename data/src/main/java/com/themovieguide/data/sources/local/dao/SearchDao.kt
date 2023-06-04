package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.SearchDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: SearchDB)

    @Query(
        "insert into search(movieKey, adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount) " +
            "select" +
            " :movieKey," +
            " :adult," +
            " :backdropPath," +
            " :genreIds, " +
            " :id, " +
            " :originalLanguage, " +
            " :originalTitle, " +
            " :overview, " +
            " :popularity, " +
            " :posterPath, " +
            " :releaseDate, " +
            " :title, " +
            " :video, " +
            " :voteAverage, " +
            " :voteCount " +
            "where not exists (select id From search where id = :id)",
    )
    abstract fun insertOnSearch(
        movieKey: Int,
        adult: Boolean,
        backdropPath: String,
        genreIds: String,
        id: Int,
        originalLanguage: String,
        originalTitle: String,
        overview: String,
        popularity: Double,
        posterPath: String,
        releaseDate: String,
        title: String,
        video: Boolean,
        voteAverage: Double,
        voteCount: Int,
    )

    @Query("select * from search where id = :movieId")
    abstract suspend fun getMovieById(movieId: Int): SearchDB

    @Query("select * from search group by dbId")
    abstract fun getMovies(): Flow<List<SearchDB>>

    @Query("select * from search group by title")
    abstract fun getMoviesByTitle(): Flow<List<SearchDB>>

    @Query("DELETE FROM search where id = :movieId")
    abstract suspend fun deleteMovie(movieId: Int)

    @Query("DELETE FROM search")
    abstract suspend fun delete()
}
