package com.themovieguide.data.sources.local.repository.theater

import com.themovieguide.data.sources.local.model.TheaterDB
import kotlinx.coroutines.flow.Flow

interface InTheaterDBRepository {
    fun insertMovie(movie: TheaterDB)
    fun getMovies(): Flow<List<TheaterDB>>
    fun getMoviesByTitle(): Flow<List<TheaterDB>>
    suspend fun getMovieById(movieId: Int): TheaterDB
    suspend fun deleteMovie(movieId: Int)
}
