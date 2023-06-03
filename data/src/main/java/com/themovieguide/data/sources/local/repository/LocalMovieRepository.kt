package com.themovieguide.data.sources.local.repository

import com.themovieguide.data.sources.local.model.MovieDB
import kotlinx.coroutines.flow.Flow

interface LocalMovieRepository {
    fun insertMovie(topRated: MovieDB)
    fun getMovies(): Flow<List<MovieDB>>
    fun getMoviesByTitle(): Flow<List<MovieDB>>
    suspend fun getMovieById(movieId: Int): MovieDB
    suspend fun deleteMovie(movieId: Int)
}
