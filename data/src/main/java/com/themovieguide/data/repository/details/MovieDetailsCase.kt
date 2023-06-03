package com.themovieguide.data.repository.details

import com.themovieguide.domain.features.details.Details
import com.themovieguide.domain.features.details.DetailsRepository
import com.themovieguide.domain.states.details.StateDetails
import com.themovieguide.domain.states.showing.StateSingleMeta
import com.themovieguide.domain.utils.EMPTY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailsCase @Inject constructor(private val repository: Details) :
    DetailsRepository {
    override suspend fun fetchMovieDetails(movieId: Int): StateDetails {
        return withContext(Dispatchers.IO) {
            when (val response = repository.movieDetails(movieId)) {
                is StateSingleMeta.OnSuccess -> {
                    StateDetails.HideLoader
                    StateDetails.OnSuccess(data = response.data)
                }
                is StateSingleMeta.OnFailed -> {
                    delay(2000)
                    StateDetails.HideLoader
                    StateDetails.OnFailed(error = response.error ?: EMPTY)
                }
                else -> {
                    StateDetails.HideLoader
                    StateDetails.OnFailed(error = "No response")
                }
            }
        }
    }
}
