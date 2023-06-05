package com.themovieguide.data.sources.local.repository.search

import com.themovieguide.data.sources.local.model.SearchDB
import kotlinx.coroutines.flow.Flow

interface SearchDBRepository {
    fun insertMovie(movie: SearchDB)
    fun insertSearch(movie: SearchDB)
    fun getMovies(title: String): Flow<List<SearchDB>>
    fun getMoviesByTitle(): Flow<List<SearchDB>>
    suspend fun getMovieById(movieId: Int): SearchDB
    suspend fun deleteMovie(movieId: Int)
    suspend fun delete()
}
