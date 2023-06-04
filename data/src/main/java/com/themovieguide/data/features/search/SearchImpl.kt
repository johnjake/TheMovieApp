package com.themovieguide.data.features.search

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.local.mapper.castToSearchDB
import com.themovieguide.data.sources.local.repository.search.SearchDBRepository
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.data.utils.Connectivity
import com.themovieguide.data.utils.SUCCESS
import com.themovieguide.domain.features.search.Search
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject
class SearchImpl @Inject constructor(
    private val api: ApiServices,
    private val storage: SearchDBRepository,
    private val signal: Connectivity
) : Search {
    override suspend fun searchMovie(query: String, page: Int): StateMeta {
        val configKey = BuildConfig.API_KEY
        return if (signal.isInternetConnected()) {
            try {
                val response = api.searchMovie(apiKey = configKey, searchKey = query, pageNumber = page)
                val result = response.results ?: emptyList()
                val data = result.toMovieList()
                data.forEach { movie ->
                    val movieDB = movie.castToSearchDB()
                    storage.insertSearch(movieDB)
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
