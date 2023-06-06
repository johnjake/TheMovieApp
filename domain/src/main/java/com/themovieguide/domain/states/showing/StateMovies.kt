package com.themovieguide.domain.states.showing

import com.themovieguide.domain.model.Movies

sealed class StateMoviesDB {
    data class OnSuccess(val data: List<Movies>) : StateMoviesDB()
    data class OnFailure(val error: String) : StateMoviesDB()
}

sealed class StateMovies {
    object HideLoader : StateMovies()
    object ShowLoader : StateMovies()
    data class OnSuccess(val data: List<Movies>) : StateMovies()
    data class OnFailure(val error: String) : StateMovies()
}
