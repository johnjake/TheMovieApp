package com.themovieguide.domain.features.details

import com.themovieguide.domain.states.details.StateDetails

interface DetailsRepository {
    suspend fun fetchMovieDetails(movieId: Int): StateDetails
}
