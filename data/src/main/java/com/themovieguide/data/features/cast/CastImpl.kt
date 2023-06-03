package com.themovieguide.data.features.cast

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toCastMovie
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.cast.MovieCast
import com.themovieguide.domain.states.cast.StateCastMeta
import javax.inject.Inject

class CastImpl @Inject constructor(private val api: ApiServices) : MovieCast {
    override suspend fun castInMovie(movieId: Int): StateCastMeta {
        val configKey = BuildConfig.API_KEY
        return try {
            val response = api.getMovieCast(
                movieId = movieId,
                apiKey = configKey,
            )
            val data = response.toCastMovie()
            StateCastMeta.OnSuccess(data = data)
        } catch (ex: Exception) {
            StateCastMeta.OnFailed(error = ex.message)
        }
    }
}
