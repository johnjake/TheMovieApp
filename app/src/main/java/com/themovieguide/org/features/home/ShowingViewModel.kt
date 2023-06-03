package com.themovieguide.org.features.home // ktlint-disable package-name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.showing.ShowingRepository
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.states.showing.StateShowing
import com.themovieguide.org.features.state.StateMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowingViewModel @Inject constructor(
    private val repository: ShowingRepository,
) : ViewModel() {
    private val showFlow: MutableSharedFlow<StateMovie<List<Movies>>> = MutableSharedFlow(replay = 1)
    val showShared: SharedFlow<StateMovie<List<Movies>>> = showFlow

    private val theaterFlow: MutableSharedFlow<StateMovie<List<Movies>>> = MutableSharedFlow(replay = 1)
    val theaterShared: SharedFlow<StateMovie<List<Movies>>> = theaterFlow

    fun getNowShowing() {
        viewModelScope.launch {
            when (val state = repository.nowShowing()) {
                is StateShowing.ShowLoader -> showFlow.emit(StateMovie.ShowLoader)
                is StateShowing.HideLoader -> showFlow.emit(StateMovie.HideLoader)
                is StateShowing.OnSuccess -> showFlow.emit(StateMovie.OnSuccess(data = state.data))
                is StateShowing.OnFailed -> showFlow.emit(StateMovie.OnFailure(error = state.error ?: "Unknown error"))
                else -> showFlow.emit(StateMovie.HideLoader)
            }
        }
    }

    fun getInTheater(page: Int) {
        viewModelScope.launch {
            when (val state = repository.inTheater(page = page)) {
                is StateShowing.ShowLoader -> theaterFlow.emit(StateMovie.ShowLoader)
                is StateShowing.HideLoader -> theaterFlow.emit(StateMovie.HideLoader)
                is StateShowing.OnSuccess -> theaterFlow.emit(StateMovie.OnSuccess(data = state.data))
                is StateShowing.OnFailed -> theaterFlow.emit(StateMovie.OnFailure(error = state.error ?: "Unknown error"))
                else -> theaterFlow.emit(StateMovie.HideLoader)
            }
        }
    }
}
