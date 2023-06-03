package com.themovieguide.data.repository.upcoming

import com.themovieguide.domain.features.upcoming.Upcoming
import com.themovieguide.domain.features.upcoming.UpcomingRepository
import com.themovieguide.domain.states.showing.StateMeta
import com.themovieguide.domain.states.showing.StateShowing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpcomingCase @Inject constructor(private val repository: Upcoming) : UpcomingRepository {
    override suspend fun fetchUpcoming(page: Int): StateShowing {
        return withContext(Dispatchers.IO) {
            when (val response = repository.upcoming(page = page)) {
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
