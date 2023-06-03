package com.themovieguide.data.features.search

import com.themovieguide.data.BuildConfig
import com.themovieguide.data.mapper.toMovieList
import com.themovieguide.data.sources.remote.ApiServices
import com.themovieguide.domain.features.search.Search
import com.themovieguide.domain.states.showing.StateMeta
import javax.inject.Inject
class SearchImpl @Inject constructor(private val api: ApiServices) : Search {
    override suspend fun searchMovie(query: String, page: Int): StateMeta {
        val configKey = BuildConfig.API_KEY
        return try {
            val response = api.searchMovie(apiKey = configKey, searchKey = query, pageNumber = page)
            val result = response.results ?: emptyList()
            val data = result.toMovieList()
            StateMeta.OnSuccess(data = data)
        } catch (ex: Exception) {
            StateMeta.OnFailed(error = ex.message)
        }
    }
}
