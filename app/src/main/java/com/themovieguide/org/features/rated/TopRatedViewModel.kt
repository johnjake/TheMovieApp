package com.themovieguide.org.features.rated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.toprated.TopRatedRepository
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.states.showing.StateShowing
import com.themovieguide.org.features.state.StateMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(private val repository: TopRatedRepository) : ViewModel() {
    private val showFlow: MutableSharedFlow<StateMovie<List<Movies>>> = MutableSharedFlow(replay = 1)
    val showShared: SharedFlow<StateMovie<List<Movies>>> = showFlow

    fun fetchTopRated(page: Int) {
        viewModelScope.launch {
            when(val state = repository.fetchTopRated(page = page)) {
                is StateShowing.ShowLoader -> showFlow.emit(StateMovie.ShowLoader)
                is StateShowing.HideLoader -> showFlow.emit(StateMovie.HideLoader)
                is StateShowing.OnSuccess -> showFlow.emit(StateMovie.OnSuccess(data = state.data))
                is StateShowing.OnFailed -> showFlow.emit(StateMovie.OnFailure(error = state.error ?: "Unknown error"))
                else -> showFlow.emit(StateMovie.HideLoader)
            }
        }
    }
}
