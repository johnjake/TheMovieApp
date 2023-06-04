package com.themovieguide.data.repository.upcoming

import com.themovieguide.data.mapper.castUpcomingFlowLists
import com.themovieguide.data.sources.local.repository.upcoming.UpcomingDBRepository
import com.themovieguide.domain.features.upcoming.Upcoming
import com.themovieguide.domain.features.upcoming.UpcomingRepository
import com.themovieguide.domain.states.showing.StateMeta
import com.themovieguide.domain.states.showing.StateShowing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpcomingCase @Inject constructor(
    private val repository: Upcoming,
    private val storage: UpcomingDBRepository
) : UpcomingRepository {
    override suspend fun fetchUpcoming(page: Int): StateShowing {
        return withContext(Dispatchers.IO) {
            when (val response = repository.upcoming(page = page)) {
                is StateMeta.OnSuccess -> {
                    StateShowing.HideLoader
                    val movieDB = storage.getMovies()
                    StateShowing.OnSuccess(data = movieDB.castUpcomingFlowLists())
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
