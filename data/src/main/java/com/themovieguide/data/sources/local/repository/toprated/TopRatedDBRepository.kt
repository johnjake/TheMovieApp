package com.themovieguide.data.sources.local.repository.toprated

import com.themovieguide.data.sources.local.model.TopRatedDB
import kotlinx.coroutines.flow.Flow

interface TopRatedDBRepository {
    fun insertMovie(movie: TopRatedDB)
    fun insertTopRated(movie: TopRatedDB)
    fun getMovies(): Flow<List<TopRatedDB>>
    fun getMoviesByTitle(): Flow<List<TopRatedDB>>
    suspend fun getMovieById(movieId: Int): TopRatedDB
    suspend fun deleteMovie(movieId: Int)
    suspend fun delete()
}
