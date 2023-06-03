package com.themovieguide.data.features.upcoming

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.upcoming.Upcoming
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject

class UpcomingImpl @Inject constructor(private val api: ApiServices) : Upcoming {
    override suspend fun upcoming(page: Int): StateMeta {
        return try {
            val buildKey = BuildConfig.API_KEY
            val dto = api.upComing(
                apiKey = buildKey,
                pageNumber = page,
            )
            val response = dto.results ?: emptyList()
            val data = response.toMovieList()
            StateMeta.OnSuccess(data = data)
        } catch (ex: Exception) {
            StateMeta.OnFailed(error = ex.message)
        }
    }
}
