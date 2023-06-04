package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.TheaterDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TheaterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: TheaterDB)

    @Query(
        "insert into inTheater(movieKey, adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount) " +
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
            "where not exists (select id From inTheater where id = :id)",
    )
    abstract fun insertOnTheater(
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

    @Query("select * from inTheater where id = :movieId")
    abstract suspend fun getMovieById(movieId: Int): TheaterDB

    @Query("select * from inTheater group by dbId")
    abstract fun getMovies(): Flow<List<TheaterDB>>

    @Query("select * from inTheater group by title")
    abstract fun getMoviesByTitle(): Flow<List<TheaterDB>>

    @Query("DELETE FROM inTheater where id = :movieId")
    abstract suspend fun deleteMovie(movieId: Int)

    @Query("DELETE FROM inTheater")
    abstract suspend fun delete()
}
