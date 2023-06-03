package com.themovieguide.data.features.theater

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.theater.Theater
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject

class TheaterImpl @Inject constructor(private val api: ApiServices) : Theater {
    private val configKey = BuildConfig.API_KEY
    override suspend fun inTheater(
        page: Int,
    ): StateMeta {
        return try {
            val response = api.inTheater(apiKey = configKey, pageNumber = page)
            val extracted = response.results ?: emptyList()
            val data = extracted.toMovieList()
            StateMeta.OnSuccess(data = data)
        } catch (ex: Exception) {
            StateMeta.OnFailed(error = ex.message)
        }
    }
}
