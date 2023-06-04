package com.themovieguide.data.sources.local.repository.upcoming

import com.themovieguide.data.sources.local.model.UpcomingDB
import kotlinx.coroutines.flow.Flow

interface UpcomingDBRepository {
    fun insertMovie(movie: UpcomingDB)
    fun insertUpcoming(movie: UpcomingDB)
    fun getMovies(): Flow<List<UpcomingDB>>
    fun getMoviesByTitle(): Flow<List<UpcomingDB>>
    suspend fun getMovieById(movieId: Int): UpcomingDB
    suspend fun deleteMovie(movieId: Int)
    suspend fun delete()
}
