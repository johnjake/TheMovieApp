package com.themovieguide.data.repository.search

import com.themovieguide.domain.features.search.Search
import com.themovieguide.domain.features.search.SearchRepository
import com.themovieguide.domain.states.showing.StateMeta
import com.themovieguide.domain.states.showing.StateShowing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
class SearchCase @Inject constructor(private val movie: Search) : SearchRepository {
    override suspend fun fetchSearch(query: String, page: Int): StateShowing {
        return withContext(Dispatchers.IO) {
            when (val response = movie.searchMovie(query = query, page = page)) {
                is StateMeta.OnSuccess -> {
                    StateShowing.HideLoader
                    StateShowing.OnSuccess(data = response.data)
                }
                is StateMeta.OnFailed -> {
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = response.error)
                }
                else -> {
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = "No response")
                }
            }
        }
    }
}
