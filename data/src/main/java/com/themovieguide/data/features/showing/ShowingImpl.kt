package com.themovieguide.data.features.showing

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieEntity
import com.themovieguide.data.sources.local.mapper.castToTheaterDB
import com.themovieguide.data.sources.local.repository.theater.InTheaterDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.showing.Showing
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject

class ShowingImpl @Inject constructor(
    private val api: ApiServices,
    private val db: InTheaterDBRepository,
    private val signal: Connectivity
) : Showing {
    private val configKey = BuildConfig.API_KEY
    override suspend fun nowShowing(): StateMeta {
        return if (signal.isInternetConnected()) {
            try {
                val response = api.inTheater(
                    apiKey = configKey,
                    languages = "en-US",
                    region = "PH",
                    pageNumber = 1,
                )
                val mapData = response.results?.toList() ?: emptyList()
                val data = mapData.map { movie ->
                    movie.toMovieEntity()
                }
                data.forEach { movie ->
                    val dbData = movie.castToTheaterDB()
                    db.insertInTheater(dbData)
                }
                StateMeta.OnSuccess(data = SUCCESS)
            } catch (ex: Exception) {
                StateMeta.OnFailed(error = ex.message)
            }
        } else {
            StateMeta.NoInternet
        }
    }
}
