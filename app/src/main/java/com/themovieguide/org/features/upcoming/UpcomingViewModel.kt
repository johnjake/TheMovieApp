package com.themovieguide.org.features.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.upcoming.UpcomingRepository
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.states.showing.StateShowing
import com.themovieguide.org.features.state.StateMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val repository: UpcomingRepository,
) : ViewModel() {
    private val showFlow: MutableSharedFlow<StateMovie<List<Movies>>> = MutableSharedFlow(replay = 1)
    val showShared: SharedFlow<StateMovie<List<Movies>>> = showFlow

    fun fetchUpcoming(page: Int) {
        viewModelScope.launch {
            when (val response = repository.fetchUpcoming(page = page)) {
                is StateShowing.ShowLoader -> showFlow.emit(StateMovie.ShowLoader)
                is StateShowing.HideLoader -> showFlow.emit(StateMovie.HideLoader)
                is StateShowing.OnSuccess -> {
                    response.data.collectLatest { movie ->
                        showFlow.emit(StateMovie.OnSuccess(data = movie))
                    }
                }
                is StateShowing.OnFailed -> showFlow.emit(StateMovie.OnFailure(error = response.error ?: "Unknown error"))
                else -> showFlow.emit(StateMovie.HideLoader)
            }
        }
    }
}
