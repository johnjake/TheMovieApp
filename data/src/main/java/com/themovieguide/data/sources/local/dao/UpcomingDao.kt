package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.UpcomingDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UpcomingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: UpcomingDB)

    @Query(
        "insert into upcoming(movieKey, adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount) " +
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
            "where not exists (select id From upcoming where id = :id)",
    )
    abstract fun insertUpcoming(
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

    @Query("select * from upcoming where id = :movieId")
    abstract suspend fun getMovieById(movieId: Int): UpcomingDB

    @Query("select * from upcoming group by dbId")
    abstract fun getMovies(): Flow<List<UpcomingDB>>

    @Query("select * from upcoming group by title")
    abstract fun getMoviesByTitle(): Flow<List<UpcomingDB>>

    @Query("DELETE FROM upcoming where id = :movieId")
    abstract suspend fun deleteMovie(movieId: Int)

    @Query("DELETE FROM upcoming")
    abstract suspend fun delete()
}
