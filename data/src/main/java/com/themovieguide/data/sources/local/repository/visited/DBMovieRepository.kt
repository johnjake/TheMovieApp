package com.themovieguide.data.sources.local.repository.visited

import com.themovieguide.data.sources.local.model.MovieDB
import kotlinx.coroutines.flow.Flow

interface DBMovieRepository {
    fun insertMovie(topRated: MovieDB)
    fun getMovies(): Flow<List<MovieDB>>
    fun getMoviesByTitle(): Flow<List<MovieDB>>
    suspend fun getMovieById(movieId: Int): MovieDB
    suspend fun deleteMovie(movieId: Int)
}
