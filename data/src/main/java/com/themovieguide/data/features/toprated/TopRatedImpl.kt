package com.themovieguide.data.features.toprated

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.local.mapper.castToTopRatedDB
import com.themovieguide.data.sources.local.repository.toprated.TopRatedDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.toprated.TopRated
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject

class TopRatedImpl @Inject constructor(
    private val api: ApiServices,
    private val db: TopRatedDBRepository,
    private val signal: Connectivity
) : TopRated {
    override suspend fun topRated(page: Int): StateMeta {
        return if(signal.isInternetConnected()) {
            try {
                val buildKey = BuildConfig.API_KEY
                val dto = api.inTopRated(
                    apiKey = buildKey,
                    pageNumber = page,
                )
                val response = dto.results ?: emptyList()
                val result = response.toMovieList()

                result.forEach { movie ->
                    val dbData = movie.castToTopRatedDB()
                    db.insertTopRated(dbData)
                }
                StateMeta.OnSuccess(data = SUCCESS)
            } catch (ex: Exception) {
                StateMeta.OnFailed(error = ex.message)
            }
        } else StateMeta.NoInternet
    }
}
