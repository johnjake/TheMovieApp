package com.themovieguide.data.repository.showing

import com.themovieguide.domain.features.showing.Showing
import com.themovieguide.domain.features.showing.ShowingRepository
import com.themovieguide.domain.states.showing.StateMeta
import com.themovieguide.domain.states.showing.StateShowing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowingCase @Inject constructor(
    private val repository: Showing,
) : ShowingRepository {
    override suspend fun nowShowing(): StateShowing {
        return withContext(Dispatchers.IO) {
            when (val response = repository.nowShowing()) {
                is StateMeta.OnSuccess -> {
                    StateShowing.HideLoader
                    StateShowing.OnSuccess(data = response.data)
                }
                is StateMeta.OnFailed -> {
                    delay(2000)
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

    override suspend fun inTheater(page: Int): StateShowing {
        return withContext(Dispatchers.IO) {
            when (val response = repository.inTheater(page = page)) {
                is StateMeta.OnSuccess -> {
                    StateShowing.HideLoader
                    StateShowing.OnSuccess(data = response.data)
                }
                is StateMeta.OnFailed -> {
                    delay(2000)
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
