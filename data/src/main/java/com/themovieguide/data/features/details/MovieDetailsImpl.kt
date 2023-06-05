package com.themovieguide.data.features.details

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieEntity
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.details.Details
import com.themovieguide.domain.states.showing.StateSingleMeta
import kotlinx.coroutines.delay
import javax.inject.Inject

class MovieDetailsImpl @Inject constructor(private val api: ApiServices) : Details {
    override suspend fun movieDetails(movieId: Int): StateSingleMeta {
        val configKey = BuildConfig.API_KEY
        return try {
            val response = api.detailsMovie(
                apiKey = configKey,
                language = "en-US",
                append = "videos",
                moviePath = movieId,
            )
            val data = response.toMovieEntity()
            delay(100)
            StateSingleMeta.OnSuccess(data = data)
        } catch (ex: Exception) {
            StateSingleMeta.OnFailed(error = ex.message)
        }
    }
}
