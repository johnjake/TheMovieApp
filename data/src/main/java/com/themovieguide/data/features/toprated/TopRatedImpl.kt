package com.themovieguide.data.features.toprated

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.toprated.TopRated
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject

class TopRatedImpl @Inject constructor(private val api: ApiServices) : TopRated {
    override suspend fun topRated(page: Int): StateMeta {
        return try {
            val buildKey = BuildConfig.API_KEY
            val dto = api.inTopRated(
                apiKey = buildKey,
                pageNumber = page,
            )
            val response = dto.results ?: emptyList()
            val result = response.toMovieList()
            StateMeta.OnSuccess(data = result)
        } catch (ex: Exception) {
            StateMeta.OnFailed(error = ex.message)
        }
    }
}
