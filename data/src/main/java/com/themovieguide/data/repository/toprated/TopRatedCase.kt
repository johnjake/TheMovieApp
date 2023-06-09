package com.themovieguide.data.repository.toprated

import com.themovieguide.data.mapper.castTopRatedFlowLists
import com.themovieguide.data.sources.local.repository.toprated.TopRatedDBRepository
import com.themovieguide.domain.features.toprated.TopRated
import com.themovieguide.domain.features.toprated.TopRatedRepository
import com.themovieguide.domain.states.showing.StateMeta
import com.themovieguide.domain.states.showing.StateShowing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopRatedCase @Inject constructor(
    private val repository: TopRated,
    private val db: TopRatedDBRepository
) : TopRatedRepository {
    override suspend fun fetchTopRated(page: Int): StateShowing {
        return withContext(Dispatchers.IO) {
            StateShowing.ShowLoader
            when (val response = repository.topRated(page = page)) {
                is StateMeta.OnSuccess -> {
                    StateShowing.HideLoader
                    val movieDB = db.getMovies()
                    StateShowing.OnSuccess(data = movieDB.castTopRatedFlowLists())
                }
                is StateMeta.OnFailed -> {
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = response.error)
                }
                is StateMeta.NoInternet -> {
                    StateShowing.HideLoader
                    val movieDB = db.getMovies()
                    StateShowing.OnSuccess(data = movieDB.castTopRatedFlowLists())
                }
                else -> {
                    StateShowing.HideLoader
                    StateShowing.OnFailed(error = "No response")
                }
            }
        }
    }
}
