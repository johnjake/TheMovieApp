package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.TopRatedDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TopRatedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: TopRatedDB)

    @Query(
        "insert into topRated(movieKey, adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount) " +
            "select" +
            " :movieKey," +
            " :adult," +
            " :backdropPath," +
            " :genreIds, " +
            " :id, " +
            ":originalLanguage, " +
            ":originalTitle, " +
            ":overview, " +
            ":popularity, " +
            ":posterPath, " +
            ":releaseDate, " +
            ":title, " +
            ":video, " +
            ":voteAverage, " +
            ":voteCount " +
            "where not exists (select id From topRated where id = :id)",
    )
    abstract fun insertOnTopRated(
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

    @Query("select * from topRated where id = :movieId")
    abstract suspend fun getMovieById(movieId: Int): TopRatedDB

    @Query("select * from topRated group by dbId")
    abstract fun getMovies(): Flow<List<TopRatedDB>>

    @Query("select * from topRated group by title")
    abstract fun getMoviesByTitle(): Flow<List<TopRatedDB>>

    @Query("DELETE FROM topRated where id = :movieId")
    abstract suspend fun deleteMovie(movieId: Int)

    @Query("DELETE FROM topRated")
    abstract suspend fun delete()
}
