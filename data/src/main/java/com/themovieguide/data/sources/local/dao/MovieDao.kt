package com.themovieguide.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themovieguide.data.sources.local.model.MovieDB
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(movie: MovieDB)

    @Query("select * from movies where id = :movieId")
    abstract suspend fun getMovieById(movieId: Int): MovieDB

    @Query("select * from movies group by title")
    abstract suspend fun getMovies(): List<MovieDB>

    @Query("select * from movies group by title")
    abstract fun getMoviesByTitle(): Flow<List<MovieDB>>

    @Query("DELETE FROM movies where id = :movieId")
    abstract suspend fun deleteMovie(movieId: Int)
}
