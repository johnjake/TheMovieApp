package com.themovieguide.data.features.upcoming

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.local.mapper.castToUpcomingDB
import com.themovieguide.data.sources.local.repository.upcoming.UpcomingDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.upcoming.Upcoming
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject

class UpcomingImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: UpcomingDBRepository,
    private val signal: Connectivity
) : Upcoming {
    override suspend fun upcoming(page: Int): StateMeta {
        return if (signal.isInternetConnected()) {
            try {
                val buildKey = BuildConfig.API_KEY
                val dto = api.upComing(
                    apiKey = buildKey,
                    pageNumber = page,
                )
                val response = dto.results ?: emptyList()
                val data = response.toMovieList()
                data.forEach { movie ->
                    val dbUpcoming = movie.castToUpcomingDB()
                    storage.insertUpcoming(dbUpcoming)
                }
                StateMeta.OnSuccess(data = SUCCESS)
            } catch (ex: Exception) {
                StateMeta.OnFailed(error = ex.message)
            }
        } else StateMeta.NoInternet
    }
}
