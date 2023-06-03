package com.themovieguide.org.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.themovieguide.domain.features.search.SearchRepository
import com.themovieguide.domain.model.Movies
import com.themovieguide.domain.states.showing.StateShowing
import com.themovieguide.org.features.state.StateMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
) : ViewModel() {
    private val searchFlow: MutableSharedFlow<StateMovie<List<Movies>>> = MutableSharedFlow(replay = 1)
    val searchShared: SharedFlow<StateMovie<List<Movies>>> = searchFlow

    fun fetchUpcoming(query: String, page: Int) {
        viewModelScope.launch {
            when (val state = repository.fetchSearch(query = query, page = page)) {
                is StateShowing.ShowLoader -> searchFlow.emit(StateMovie.ShowLoader)
                is StateShowing.HideLoader -> searchFlow.emit(StateMovie.HideLoader)
                is StateShowing.OnSuccess -> searchFlow.emit(StateMovie.OnSuccess(data = state.data))
                is StateShowing.OnFailed -> searchFlow.emit(StateMovie.OnFailure(error = state.error ?: "Unknown error"))
                else -> searchFlow.emit(StateMovie.HideLoader)
            }
        }
    }
}
