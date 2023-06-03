package com.themovieguide.data.features.showing

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieEntity
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.showing.Showing
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject

class ShowingImpl @Inject constructor(
    private val api: ApiServices,
) : Showing {
    private val configKey = BuildConfig.API_KEY
    override suspend fun nowShowing(): StateMeta {
        return try {
            val response = api.nowShowing(
                apiKey = configKey,
                languages = "en-US",
                pageNumber = 1,
            )
            val mapData = response.results?.toList() ?: emptyList()
            val data = mapData.map {
                it.toMovieEntity()
            }
            StateMeta.OnSuccess(data = data)
        } catch (ex: Exception) {
            StateMeta.OnFailed(error = ex.message)
        }
    }

    override suspend fun inTheater(page: Int): StateMeta {
        return try {
            val response = api.inTheater(
                apiKey = configKey,
                languages = "en-US",
                region = "PH",
                pageNumber = page,
            )
            val list = response.results ?: emptyList()
            val data = list.toMovieList()
            StateMeta.OnSuccess(data = data)
        } catch (ex: Exception) {
            StateMeta.OnFailed(error = ex.message)
        }
    }
}
