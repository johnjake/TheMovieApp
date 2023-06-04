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

    @Query("select * from inTheater where id = :movieId")
    abstract suspend fun getMovieById(movieId: Int): MovieDB

    @Query("select * from inTheater group by dbId")
    abstract fun getMovies(): Flow<List<MovieDB>>

    @Query("select * from inTheater group by title")
    abstract fun getMoviesByTitle(): Flow<List<MovieDB>>

    @Query("DELETE FROM inTheater where id = :movieId")
    abstract suspend fun deleteMovie(movieId: Int)
}
