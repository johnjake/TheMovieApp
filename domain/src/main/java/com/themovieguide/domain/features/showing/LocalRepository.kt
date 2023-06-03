package com.themovieguide.domain.features.showing

import com.themovieguide.domain.model.Movie
import com.themovieguide.domain.states.showing.StateMoviesDB

interface LocalRepository {
    fun insertMovie(movie: Movie)
    suspend fun getMovies(): StateMoviesDB
}
